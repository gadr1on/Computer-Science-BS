# Code by Kevin Hernandez Vazquez
# 4/19/2019
# This code calculates the area of a triangle
.data
	message: .asciiz "A = " # Message that will contain the result.

.text
	li $t0, 0   # Initialize Area varianle with zero.
	li $t1, 1   # Height variable
	li $t2, 2   # Base variable
	
	mul $t0, $t1, $t2  # Multiply the Base by the Height
	div $t0, $t0, 2    # Divide the previous result by two
	
	# Prints the message data "A = "
	li $v0, 4
	la $a0, message
	syscall
	
	# Combines the data message with the result and prints it.
	li $v0, 1
	la $a0, ($t0)
	syscall
	
	li $v0, 10 # Terminate the code.
	syscall
	
	
