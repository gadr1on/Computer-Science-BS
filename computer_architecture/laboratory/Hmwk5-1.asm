.data
number:	.asciiz "Enter number #"
colon:	.asciiz " = "
result:	.asciiz "Result = "

.text  
.globl main 

	li $t0, 0     # Result 
	li $t1, 1     # Loop Count
	li $t2, 0     # Max
	li $t3, 0     # Min
	
main:
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
    	
    	beq $t1, 1, first
    	bgt $t0, $t2, max
    	move $t3, $t0 # min
    	
    	add $t1, $t1, 1
    	j main

first:
	move $t2, $t0
	move $t3, $t0
	add $t1, $t1, 1
	j main

max:
	move $t2, $t0
	add $t1, $t1, 1
	j main

done:
	sub $t0, $t2, $t3
	
	li  $v0, 4  
    	la  $a0, result  
    	syscall
	
	li $v0, 1
	move $a0, $t0
	syscall
	
	li  $v0, 10 
    	syscall
