.data
size: 		.word 5
array: 		.word 1, 4, 2, 6, 7
message1: 	.asciiz "\nArray: "
message2: 	.asciiz "\nCalc: "
message3: 	.asciiz "\nAdd Integer: "
instruction: 	.asciiz "\nMenu(enter an int as your choice):\n"
option1: 	.asciiz "1. addElement() \n"
option2: 	.asciiz "2. calcular() \n"

.text  
.globl main

main:
	li $t0, 0 	# calculation #1
	li $t1, 1	# calculation #2
	la $s0, size   	# initialize registers
	lw $s1, 0($s0)	# $s1 = size

	# <for loop>	
	li $s5, 0 	# $s5 = i
	la $s6, array	# $s6 = &array
	la $s4, array	# $s4 = &array
	la $t3, array   # For printing array list
	li $t4, 0
    	
    	j menu
    	
menu:   # Menu with options for the user to choose
	la $a0,instruction # print string str1
	li $v0,4
	syscall

	la $a0,option1 # print string str1
	li $v0,4
	syscall

	la $a0,option2 # print string str1
	li $v0,4
	syscall
	
	li $v0,5 #input choice(t0)
	syscall
	la $t0,($v0)

	beq $t0,1,adde
	beq $t0,2,calc
	beq $t0,3,done
	
	j done

########### ADD ELEMENT #############
adde:	
	#li $s3, 4
	#li $s0, 6
	
	la $a0,message3 # print string str1
	li $v0,4
	syscall
	
	li $v0,5 #input choice(t0)
	syscall
	la $s0,($v0)
	
	add $s1, $s1, 1
	sw $s0, 20($s6)
	addi $s4, $s4, 4 	# move array pointer

	j printArray
########### END ADD ELEMENT #############

################# CALCULAR ##############
calc:    	
    	lw $s7, 0($s6)  	# $s7 = array[i]
    	
    	beqz $s5, mult1
    	beq $s5, 1, sum1
    	beq $s5, 2, divi
    	beq $s5, 3, mult2
    	beq $s5, 4, mult2
    	beq $s5, 5, sum2
    	
    	la $a0, message2
	li $v0, 4
	syscall
    	
    	# syscall to print value
    	li $v0, 1      
    	move $a0, $t0
    	syscall
    	
    	la $a0, message1
	li $v0, 4
	syscall
    	
    	j printArray
    	
mult1:
	mul $t0, $s7, 2
	j update_L1
mult2:
	mul $t1, $t1, $s7
	j update_L1
sum1:
	add $t0, $t0, $s7
	j update_L1
sum2:
	add $t0, $t0, $t1
	j update_L1
divi:
	div $t0, $t0, $s7
	j update_L1
################### END OF CALCULAR ####################

printArray:
    	bge $t4, $s1, main

    	# load word from addrs and goes to the next addrs
    	lw $t2, 0($t3)
    	addi $t3, $t3, 4

    	# syscall to print value
    	li $v0, 1      
    	move $a0, $t2
    	syscall
    
    	# optional - syscall number for printing character (space)
    	li $a0, 32
    	li $v0, 11  
    	syscall


    	# increment counter
    	addi $t4, $t4, 1
    	j printArray



update_L1:
	addi $s5, $s5, 1 	# i++
	addi $s6, $s6, 4 	# move array pointer
	j calc			# go to loop2

done:
	li $v0, 10 
    	syscall
