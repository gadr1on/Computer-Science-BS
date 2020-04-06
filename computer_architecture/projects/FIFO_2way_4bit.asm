# Algorithm: FIFO
# Mapping: 2way
# Block Size: 4

.data
input:		.word 	1,2,3,4,2,5,6,2,7,8,2,4,9,2,12,13,5,2,8,15
set1:       	.word  	0,0
set2:		.word  	0,0

.text

la $t0, input
la $s1, set1
la $s2, set2

begin:
	beq $k0, 19, done # done counter
	li $t2, 0
	lw $t3, 0($t0)
	la $s3, set1
	la $s4, set2
	
checkHit:
    	beq $t2, 2, setOne 
    	
    	lw $t4, 0($s3)
    	lw $t5, 0($s4)
    	beq $t4, $t3, addHit
    	beq $t5, $t3, addHit
    	addi $s3, $s3, 4
    	addi $s4, $s4, 4
    	addi $t2, $t2, 1
    	
    	j checkHit

addHit:
	addi $t6, $t6, 1	
	j next
  
setOne:
	beq $t7, 1, setTwo
	lw $s4, 0($s1)
	sw $s4, 4($s1)
	sw $t3, 0($s1)
	addi $t7, $t7, 1
	j next

setTwo:
	beq $t7, 0, setOne
	lw $s4, 0($s2)
	sw $s4, 4($s2)
	sw $t3, 0($s2)
	subi $t7, $t7, 1
	j next

next:
	addi $t0, $t0, 4
	addi $k0, $k0, 1
	j begin

done:
	li $v0, 1      
    	move $a0, $t6
    	syscall
    	
    	li $v0, 10
	syscall
