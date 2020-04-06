
.data 
	integers: .asciiz "Two integers: 45 100"
	
.text


li $v0, 4  
la $a0, integers
syscall

main:
	li $v0, 4  
	la $a0, integers
	syscall	
			
li $v0, 10
syscall
	

		
