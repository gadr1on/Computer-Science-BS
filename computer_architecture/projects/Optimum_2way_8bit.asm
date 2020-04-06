# Algorithm: Optimo
# Mapping: 2way
# Block Size: 8

.data
input:		.word 	1,2,3,4,2,5,6,2,7,8,2,4,9,2,12,13,5,2,8,15
set1:       	.word  	0,0
set2:		.word  	0,0
set3:       	.word  	0,0
set4:		.word  	0,0

.text

la $t0, input # main input
la $t1, input # future input
la $s1, set1
la $s2, set2
la $s3, set3
la $s4, set4
li $a3, 20

begin:
	beq $k0, 20, done # done counter
	li $t2, 0
	lw $t3, 0($t0)
	
checkHit:
    	beq $t2, 2, setOne 
    	
    	lw $t4, 0($s1)
    	lw $t5, 0($s2)
    	beq $t4, $t3, addHit
    	beq $t5, $t3, addHit
    	lw $t4, 0($s3)
    	lw $t5, 0($s4)
    	beq $t4, $t3, addHit
    	beq $t5, $t3, addHit
    	addi $s1, $s1, 4
    	addi $s2, $s2, 4
    	addi $s3, $s3, 4
    	addi $s4, $s4, 4
    	addi $t2, $t2, 1
    	
    	j checkHit

addHit:
	addi $t6, $t6, 1
	li $t8, 0 # se1pos count
	li $t9, 0 # set2pos count
	li $fp, 0 # set3pos count
	li $ra, 0 # set4pos count
	la $s1, set1
	la $s2, set2
	la $s3, set3
	la $s4, set4
	j next
  
setOne:
	la $s1, set1
	la $s2, set2
	la $s3, set3
	la $s4, set4
	beq $t7, 1, setTwo
	beq $t7, 2, setThree
	beq $t7, 3, setFour
	move $t1, $t0
	li $s6, 0
	li $a1, 0 # rep1 count
	li $a2, 0 # rep2 count
	
	set1ReadFuture:
	beq $s6, $a3, rep1Check # restar el 20!!!
	
	lw $at, 0($t1)
	lw $t4, 0($s1)
	lw $t5, 4($s1)
	
	bne $at, $t4, set1jump1
	addi $a1, $a1, 1
	set1jump1:
	bne $at, $t5, set1jump2
	addi $a2, $a2, 1
	set1jump2:

	addi $t1, $t1, 4
	addi $s6, $s6, 1
	j set1ReadFuture
	rep1Check:
	subi $a3, $a3, 1 # subtract 1 to 20
	
	beq $a1, $a2, set1Pos1
	blt $a1, $a2, set1First
	sw $t3, 4($s1)
	j doneSet1
	set1First:
	sw $t3, 0($s1)
	j  doneSet1
	set1Pos1:
	bnez $t8, set1Pos2
	sw $t3, 0($s1)
	j doneSet1
	set1Pos2:
	sw $t3, 4($s1)
	doneSet1:
	bnez $t8, finish1
	addi $t8, $t8, 1
	finish1:
	subi $t8, $t8, 1
	addi $t7, $t7, 1
	j next

setTwo:
	move $t1, $t0
	li $s6, 0
	li $a1, 0 # rep1 count
	li $a2, 0 # rep2 count
	
	set2ReadFuture:
	beq $s6, $a3, rep2Check # restar el 20!!!
	
	lw $at, 0($t1)
	lw $t4, 0($s2)
	lw $t5, 4($s2)
	
	bne $at, $t4, set2jump1
	addi $a1, $a1, 1
	set2jump1:
	bne $at, $t5, set2jump2
	addi $a2, $a2, 1
	set2jump2:

	addi $t1, $t1, 4
	addi $s6, $s6, 1
	j set2ReadFuture
	rep2Check:
	subi $a3, $a3, 1 # subtract 1 to 20
	
	beq $a1, $a2, set2Pos1
	blt $a1, $a2, set2First
	sw $t3, 4($s2)
	j doneSet2
	set2First:
	sw $t3, 0($s2)
	j  doneSet2
	set2Pos1:
	bnez $t9, set2Pos2
	sw $t3, 0($s2)
	j doneSet2
	set2Pos2:
	sw $t3, 4($s2)
	doneSet2:
	bnez $t9, finish2
	addi $t9, $t9, 1
	finish2:
	subi $t9, $t9, 1
	addi $t7, $t7, 1
	j next

setThree:
	move $t1, $t0
	li $s6, 0
	li $a1, 0 # rep1 count
	li $a2, 0 # rep2 count
	
	set3ReadFuture:
	beq $s6, $a3, rep3Check # restar el 20!!!
	
	lw $at, 0($t1)
	lw $t4, 0($s3)
	lw $t5, 4($s3)
	
	bne $at, $t4, set3jump1
	addi $a1, $a1, 1
	set3jump1:
	bne $at, $t5, set3jump2
	addi $a2, $a2, 1
	set3jump2:

	addi $t1, $t1, 4
	addi $s6, $s6, 1
	j set3ReadFuture
	rep3Check:
	subi $a3, $a3, 1 # subtract 1 to 20
	
	beq $a1, $a2, set3Pos1
	blt $a1, $a2, set3First
	sw $t3, 4($s3)
	j doneSet3
	set3First:
	sw $t3, 0($s3)
	j  doneSet3
	set3Pos1:
	bnez $fp, set3Pos2
	sw $t3, 0($s3)
	j doneSet3
	set3Pos2:
	sw $t3, 4($s3)
	doneSet3:
	bnez $fp, finish3
	addi $fp, $fp, 1
	finish3:
	subi $fp, $fp, 1
	addi $t7, $t7, 1
	j next

setFour:
	move $t1, $t0
	li $s6, 0
	li $a1, 0 # rep1 count
	li $a2, 0 # rep2 count
	
	set4ReadFuture:
	beq $s6, $a3, rep4Check # restar el 20!!!
	
	lw $at, 0($t1)
	lw $t4, 0($s4)
	lw $t5, 4($s4)
	
	bne $at, $t4, set4jump1
	addi $a1, $a1, 1
	set4jump1:
	bne $at, $t5, set4jump2
	addi $a2, $a2, 1
	set4jump2:

	addi $t1, $t1, 4
	addi $s6, $s6, 1
	j set4ReadFuture
	rep4Check:
	subi $a3, $a3, 1 # subtract 1 to 20
	
	beq $a1, $a2, set4Pos1
	blt $a1, $a2, set4First
	sw $t3, 4($s4)
	j doneSet4
	set4First:
	sw $t3, 0($s4)
	j  doneSet4
	set4Pos1:
	bnez $t9, set4Pos2  ###
	sw $t3, 0($s4)
	j doneSet4
	set4Pos2:
	sw $t3, 4($s4)
	doneSet4:
	bnez $ra, finish4 
	addi $ra, $ra, 1 
	finish4:
	subi $ra, $ra, 1 
	li $t7, 0
	j next

next:
	addi $t0, $t0, 4 # go to next input
	addi $k0, $k0, 1 # done counter
	j begin

done:
	li $v0, 1      
    	move $a0, $t6
    	syscall
    	
    	li $v0, 10
	syscall
