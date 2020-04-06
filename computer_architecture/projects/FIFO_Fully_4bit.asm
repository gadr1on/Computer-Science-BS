# Algorithm: FIFO
# Mapping: Fully
# Block Size: 4

.data
saves:		.word  	3
save:		.word  	0, 0, 0
caches:       	.word  	4
cache:       	.word  	0, 0, 0, 0
nline:		.asciiz "\n"

.text
.globl main

main:
	la $s2, caches  # initialize registers
	lw $s3, 0($s2)	# $s1 = size
	la $t6, cache
	
	la $s4, saves  # initialize registers
	lw $s5, 0($s4)	# $s1 = size
	la $t7, save

    	j addCache
    	
addCache:
	li $v0, 5  # input number
	syscall
	move $s7, $v0

	li $v0, 4
    	la $a0, nline
    	syscall
    	
    	la $t6, cache
    	la $t7, save
    	li $t5, 0
    	
    	j checkHit
  
begin:
	la $t6, cache
	la $t7, save
    	li $t5, 0
    	
    	j saveValues
    	
reset:
	la $t6, cache
	la $t7, save
    	li $t5, 0
    	
    	j moveElements

resetPrint:
	la $t6, cache
	la $t7, save
    	li $t5, 0
    	
    	sw $s7, 0($t6)
    	
    	j printCache
    	
printArray:
	la $t6, cache
	la $t7, save
    	li $t5, 0
    	
    	j printCache
    	
saveValues:
	bge $t5, $s5, reset

	lw $t2, 0($t6)
	addi $t6, $t6, 4
	
    	sw $t2, 0($t7)
    	addi $t7, $t7, 4

    	addi $t5, $t5, 1
   
	j saveValues

moveElements:
	bge $t5, $s5, resetPrint
	
	lw $t2, 0($t7)
	addi $t7, $t7, 4
	addi $t6, $t6, 4
	sw $t2, 0($t6)

    	# increment counter
    	addi $t5, $t5, 1
	
	j moveElements


checkHit:
	bge $t5, $s3, begin

    	# load word from addrs and goes to the next addrs
    	lw $t2, 0($t6)
    	addi $t6, $t6, 4
    	
    	beq $t2, $s7, printArray

    	# increment counter
    	addi $t5, $t5, 1
	
	j checkHit
    	
printCache:
    	bge $t5, $s3, addCache

    	# load word from addrs and goes to the next addrs
    	lw $t2, 0($t6)
    	addi $t6, $t6, 4

    	# syscall to print value
    	li $v0, 1      
    	move $a0, $t2
    	syscall
    
    	# optional - syscall number for printing character (space)
    	li $a0, 32
    	li $v0, 11  
    	syscall

    	# increment counter
    	addi $t5, $t5, 1
    	j printCache
