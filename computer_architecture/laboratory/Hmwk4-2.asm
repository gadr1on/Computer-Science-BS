.data
	num: .asciiz "num = "
	x: .asciiz "x = "
	y: .asciiz " y = "

.text
	li $t0, 0  # num
	li $t1, 2  # x = 2
	li $t2, 1  # y = 1
	
	# Print "num ="
	li $v0, 4  
	la $a0, num
	syscall
	
	# Ask user for integer
	li $v0, 5
	syscall
	move $t0, $v0
	
	blt $t0, 10, first # if num < 10
	bgt $t0, 20, first # or num > 20
	
	# else
	ble $t0, 15, second # if num <= 15
	# else
	add $t2, $t2, $t1   # y = y + x
	j final
	
first:	# if num < 10
	add $t1, $t1, 1 # x += 1
	j final
	
second: # if num <= 15
	bge $t0, 20, third 	# if num >=12 
	# else
	add $t2, $t2, 4 	# y += 4
	j final
	
third:  # if num >= 12
	add $t2, $t2, 1 # y += 1
	j final
	
final:
	# Print value of x
	li $v0, 4  
	la $a0, x
	syscall
	
	li $v0, 1
	move $a0, $t1
	syscall 
	
	# Print value of y
	li $v0, 4  
	la $a0, y
	syscall
	
	li $v0, 1
	move $a0, $t2
	syscall 
	
	# End program
	li $v0, 10
	syscall
