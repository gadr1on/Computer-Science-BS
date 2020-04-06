.data
number:	.asciiz "Enter number #"
colon:	.asciiz " = "
result:	.asciiz "Result = "

.text  
.globl main 

	li $t1, 1     # Loop Count
	
main:
	beq $t1, 4, done
	
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
    	
    	add $t1, $t1, 1
    	j main

done:
	li  $v0, 10 
    	syscall
