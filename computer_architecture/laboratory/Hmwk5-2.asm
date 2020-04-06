.data
number:	.asciiz "Enter number #"
colon:	.asciiz " = "
posi:	.asciiz "Positives = "
nega:	.asciiz " Negatives = "

.text  
.globl main 

	li $t1, 1     # Loop Count
	li $t2, 0     # + count
	li $t3, 0     # - count
	
main:  # Loop
	beq $t1, 5, done
	
	li  $v0, 4  
    	la  $a0, number  
    	syscall
    	
    	li $v0, 1
	move $a0, $t1
	syscall 
	
	li  $v0, 4  
    	la  $a0, colon  
    	syscall

	li $v0, 5  # input number
	syscall
	move $t0, $v0
    	
    	bgtz $t0, positive	# if input > 0
    	add $t3, $t3, 1		# negative count
    	add $t1, $t1, 1		# loop count
    	j main

positive:
	add $t2, $t2, 1		# positive count
	add $t1, $t1, 1		# loop count
	j main

done:	# print number of - and +
	li  $v0, 4  
    	la  $a0, posi  
    	syscall
	
	li $v0, 1
	move $a0, $t2
	syscall
	
	li  $v0, 4  
    	la  $a0, nega  
    	syscall
	
	li $v0, 1
	move $a0, $t3
	syscall
	
	li  $v0, 10 
    	syscall
