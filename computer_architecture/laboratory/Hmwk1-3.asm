
.data 
	oracion: .asciiz "Cual es tu edad? "
	oracion2: .asciiz " es mucho tiempo..."
	
.text
.globl main

main:
	li $v0, 4  
	la $a0, oracion
	syscall
	
	li $v0, 5
	syscall
	move $t0, $v0
	
	li $v0, 1
	move $a0, $t0
	syscall
	
	li $v0, 4
	la $a0, oracion2
	syscall
	
	li $v0, 10
	syscall
		
