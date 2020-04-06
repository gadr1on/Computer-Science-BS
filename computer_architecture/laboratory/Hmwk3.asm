.data
	celcius: .asciiz "Celsius = "    # Celsius message output
	farent: .asciiz "Fahrenheit = "  # Fahrenheit message output

.text
	li $t1, 0   # Fahrenheit variable initialized with zero
	
	# Printing the Celsius display message for the input
	li $v0, 4  
	la $a0, celcius
	syscall
	
	# Asks the user for the input. Celsius value in this case.
	li $v0, 5
	syscall
	move $t0, $v0
	
	mul $t1, $t0, 9   # Multiply by 9
	div $t1, $t1, 5   # Divide by 5
	add $t1, $t1, 32  # Add 32
	
	# Printing the Fahrenheit message for the result.
	li $v0, 4
	la $a0, farent
	syscall
	
	# Combines the previous Fahrenheit message with the result value.
	li $v0, 1
	move $a0, $t1
	syscall
	
	li $v0, 10 # Terminates the program.
	syscall
