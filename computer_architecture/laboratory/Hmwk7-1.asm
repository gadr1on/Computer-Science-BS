.data
size: 		.word 10
array: 		.word 2, 3, 4, 5, 1, 0, 8, 9, 6, 7

.text  
.globl main

main:
	li $t0, 0 	# avarege variable
	la $s0, size   	# initialize registers
	lw $s1, 0($s0)	# $s1 = size

	# <for loop>	
	li $s5, 0 	# $s5 = i
	la $s6, array	# $s6 = &array
	la $s3, array	# $s3 = &array
    	
    	j L1

L1:    	
	bge $s5, $s1, avg	# finish when $s5==$s1
    	lw $s7, 0($s6)  	# $s7 = A[i]
    	
    	add $t1, $t1, $s7
    	j update_L1

L2:
    	bge $s2, $s1, done	# finish when $s2==$s1
    	lw $s4, 0($s3)  	# $s4 = B[i]
    	bgt $s4, $t1, bigger
    	
    	j update_L2

bigger:
	# syscall to print value
    	li $v0, 1      
    	move $a0, $s4
    	syscall
    	
    	# Syscall number for printing character (space)
    	li $a0, 32
    	li $v0, 11  
    	syscall
    	
    	j update_L2

avg:
	div $t1, $t1, $s1
	
    	j L2

update_L1:
	addi $s5, $s5, 1 	# i++
	addi $s6, $s6, 4 	# move array pointer
	j L1 			# go to loop2

update_L2:
	addi $s2, $s2, 1 	# i++
	addi $s3, $s3, 4 	# move array pointer
	j L2 			# go to loop1

done:
	li $v0, 10 
    	syscall
