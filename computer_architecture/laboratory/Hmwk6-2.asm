.data
size: 		.word 7
A: 		.word 5, 8, 9, 4, 6, 7, 10
B: 		.word 1, 2, 3, 9, 11, 8, 4

.text  
.globl main

main:
	la $s0, size   	# initialize registers
	lw $s1, 0($s0)	# $s1 = size

	# <for loop>	
	li $s5, 0 	# $s5 = i
	la $s6, A	# $s6 = &A
    	
    	j L1

L1:    	
	bge $s5, $s1, done	# finish when $s5==$s1
    	lw $s7, 0($s6)  	# $s7 = A[i]
    	
    	li $s2, 0 		# $s2 = i
    	la $s3, B       	# $s7 = &B
    	j update_L1

L2:
    	bge $s2, $s1, L1	# finish when $s2==$s1
    	lw $s4, 0($s3)  	# $s4 = B[i]
    	beq $s4, $s7, in
    	
    	j update_L2

in:
	# syscall to print value
    	li $v0, 1      
    	move $a0, $s4
    	syscall
    	
    	# Syscall number for printing character (space)
    	li $a0, 32
    	li $v0, 11  
    	syscall
    	
    	j update_L2

update_L1:
	addi $s5, $s5, 1 	# i++
	addi $s6, $s6, 4 	# move array pointer
	j L2 			# go to loop2

update_L2:
	addi $s2, $s2, 1 	# i++
	addi $s3, $s3, 4 	# move array pointer
	j L2 			# go to loop1

done:
	li $v0, 10 
    	syscall
