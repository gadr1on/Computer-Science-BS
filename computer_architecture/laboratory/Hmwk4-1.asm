.data
	mensaje: .asciiz "Enter a number: "

.text
	# Print the message
	li $v0, 4    
	la $a0, mensaje
	syscall
	
	# Wait for user input (Integer)
	li $v0, 5
	syscall
	move $t0, $v0
	
	blt $t0, 10, first 	# if x < 10
	bge $t0, 10, second 	# if x >= 10
	bgt $t0, 20, third 	# if x > 20
	
	
first:	# if x < 10, y==5
	li $t0, 5
	j final
	
second:	# if x >= 10 or x <= 20, y==x+1
	bgt $t0, 20, third
	add $t0, $t0, 1
	j final
	
third:	# if x > 20, y==4x
	mul $t0, $t0, 4
	j final
	
final:	
	# print the result 
	li $v0, 1
	move $a0, $t0
	syscall 
	
	# end program
	li $v0, 10
	syscall
