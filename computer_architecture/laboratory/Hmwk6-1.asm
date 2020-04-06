.data
input:	.asciiz "Enter number: "
message1:	.asciiz "Ya esta en Arreglo\n"
txt7:		.asciiz "Replace index: "
size: 		.word 8
list: 		.word 3, 0, 1, 2, 6, -2, 4, 7

.text  
.globl main

main:
	li $t1, 0       # flag to check if the value is in the array
	la $s0, size   	# initialize registers
	lw $s1, 0($s0)	# $s1 = size

	# <for loop>	
	li $s5, 0 	# $s5 = i
	la $s6, list	# $s6 = &list
	
	# For printing array list
	la $t3, list
	
	# Ask user for input
	li  $v0, 4  
    	la  $a0, input 
    	syscall

	li $v0, 5  		# input number
	syscall
	move $t0, $v0
	
	li $v0, 0
    	la $t1, list
    	
    	j L1  	# jump to first loop

L1:    	
	bge $s5, $s1, done	# finish when $s5==$s1
    	lw $s7, 0($s6)  	# $s7 = list[i]
    	beq $t0, $s7, in	# if number is in array, go to in
    	
    	j update
    	
in:
	# Display message "Ya esta en Arreglo"
	li $v0, 4  
    	la $a0, message1 
    	syscall
    	
    	add $t5, $t5, 1   	# Add 1 to flag
    	
	j printArray

printArray:
    	bge $t4, 8, done

    	# load word from addrs and goes to the next addrs
    	lw $t2, 0($t3)
    	addi $t3, $t3, 4

    	# syscall to print value
    	li $v0, 1      
    	move $a0, $t2
    	syscall
    
    	# Syscall number for printing space char
    	li $a0, 32
    	li $v0, 11  
    	syscall


    	# increment counter
    	addi $t4, $t4, 1
    	j printArray

update:
	addi $s5, $s5, 1 	# i++
	addi $s6, $s6, 4 	# move array pointer
	j L1 			# go to loop 1

replace: # Replace the first element or minimum
	la $a1, list

	la $a0,txt7 	# print string str1
	li $v0,4
	syscall

	li $v0,5 	# input first no.(t0)
	syscall
	la $t0,($v0)

	mul $t6,$t6,4
	add $a1,$a1,$t6

	sw $t0,($a1)
	
	add $t5, $t5, 1  # Turn flag
	j printArray

done:
	beqz $t5, replace
	li $v0, 10 
    	syscall
