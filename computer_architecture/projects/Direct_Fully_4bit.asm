# Mapping: Direct
# Block Size: 4

.data
caches:         .word 4			
cache:          .word 0, 0, 0, 0
nline:		.asciiz "\n"

.text
.globl main

main:
	la $s2, caches  # initialize registers
	lw $s3, 0($s2)	# $s3 = size of the cache
	la $t6, cache   # contains the elements of the array

    	j addCache
    	
addCache:
	li $v0, 5  # input number
	syscall
	move $s7, $v0

	li $v0, 4
    	la $a0, nline
    	syscall
    	
    	la $t6, cache
    	li $t5, 0
    	
    	j checkHit
  
begin:
	la $t6, cache
    	li $t5, 0
    	
    	beq $t4, 0, onebit
    	beq $t4, 1, twobit
    	beq $t4, 2, threebit
    	beq $t4, 3, fourbit
    	
    	j printCache

onebit:
	sw $s7, 0($t6)
	addi $t4, $t4, 1
	j printCache
twobit:
	sw $s7, 4($t6)
	addi $t4, $t4, 1
	j printCache
threebit:
	sw $s7, 8($t6)
	addi $t4, $t4, 1
	j printCache
fourbit:
	sw $s7, 12($t6)
	#li $t4, 0
    	li $t4, 0
	
	j printCache

checkHit:
	bge $t5, $s3, begin

    	# load word from addrs and goes to the next addrs
    	lw $t2, 0($t6)
    	addi $t6, $t6, 4
    	
    	beq $t2, $s7, resetPrint

    	# increment counter
    	addi $t5, $t5, 1
	
	j checkHit

resetPrint:
	la $t6, cache
    	li $t5, 0
    	j printCache
    	
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
