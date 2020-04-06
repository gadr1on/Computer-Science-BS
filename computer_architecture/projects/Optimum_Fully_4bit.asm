# Mapping: Optimum Fully
# Block Size: 4

.data
inputs:   	.word  	16
input:  	.word  	1,2,3,4,2,5,6,2,7,8,2,4,9,2,12,13,5,2,8,15
caches:        	.word 	4			
cache:        	.word 	0, 0, 0, 0
nline:		.asciiz "\n"
bit:		.asciiz "<< "

.text
.globl main

main:
	la $s2, caches  # initialize registers
	lw $s3, 0($s2)	# $s3 = size of the cache
	la $t6, cache   # contains the elements of the array

	la $s4, inputs  # initialize input register
	lw $s5, 0($s4)	# $s5 = size of the input array
	la $t3, input   # contains the elements of the input array
	
	la $t9, input	# to save input position
	la $t8, cache	# to save cache position
	la $k0, input
	li $at, 4
	addi $t9, $t9, 16
	
    	j addCache
    	
addCache:
	lw $t2, 0($t3)
    	addi $t3, $t3, 4
	   
    	move $s7, $t2
    	
    	beq $s6, 20, done	# done when all the inputs got entered
	addi $s6, $s6, 1	# input index count

	li $v0, 4
    	la $a0, nline
    	syscall
    	
    	la $t6, cache
    	li $t5, 0
    	
    	j checkHit

checkHit:
	bge $t5, $s3, begin

    	# load word from addrs and goes to the next addrs
    	lw $t2, 0($t6)
    	addi $t6, $t6, 4

    	beq $t2, $s7, resetPos

    	# increment counter
    	addi $t5, $t5, 1

	j checkHit  

begin:
	la $t6, cache
    	li $t5, 0
    	
    	bge $t7, 1, beginFuture
    	
    	j begin2

begin2:
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
	addi $t7, $t7, 1
    	#li $t4, 0
	j printCache

resetPos:
	la $t8, cache
	la $t6, cache
	addi $t9, $t9, 4
	addi $at, $at, 1
	subi $s5, $s5, 1
   	li $t5, 0
   	li $ra, 0
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

beginFuture:
	move $t5, $ra	# save were cache left counter
	#################### DEBUG
	#li $v0, 1      
    	#move $a0, $ra  # TEST!!!!!
    	#syscall
    	
    	#li $v0, 4
    	#la $a0, ar
    	#syscall
    	######################
	move $t6, $t8	# save regiter were it left
	j readFutureCache

readFutureCache: #Cache			# s1 tiene que disminuir 1 cada vez que va por un elemento
	beq $t5, $s3, resetFuture	# jump to set t4
	
	# For the nested loop
	move $k0, $t9 	# this is probably wrong // Para saber en que elemento de input se queda
	move $t0, $at	# Nested loop counter were it left off
	
    	# load word from addrs and goes to the next addrs
    	lw $t1, 0($t6)
    	
    	############### DEBUG ##############
    	#li $a0, 32
    	#li $v0, 11
    	#syscall
    	
    	#li $v0, 1      
    	#move $a0, $t5  # TEST!!!!!
    	#syscall
    	
    	#li $v0, 1      
    	#move $a0, $t1  # TEST!!!!!
    	#syscall
    	
   	#li $v0, 4
    	#la $a0, bit
    	#syscall
    	###################################
   	
	j readFutureInput

readFutureInput:  #Input
	beq $t0, $s5, replaceBit # change Counter variable !!!!!!!!! // Aqui tengo que brincar a una funcion que guarde el elemento como el menos repetido
	
	# load word from addrs and goes to the next addrs
    	lw $t2, 0($k0)
    	addi $k0, $k0, 4
    	
    	########### DEBUG #############
    	#li $v0, 1      
    	#move $a0, $t2  # TEST!!!!!
    	#syscall
    	###############################
    	
    	beq $t1, $t2, startOver

    	# increment counter
    	addi $t0, $t0, 1

	j readFutureInput
	
startOver:
	li $at, 0 ### NOT HERE
	beq $t5, $s3, setZeroCounter
	addi $t6, $t6, 4
	addi $t5, $t5, 1
	j readFutureCache

setZeroCounter:
	li $t5, 0
	j readFutureCache

resetFuture:
	la $t6, cache
	li $t5, 0
	j readFutureCache

replaceBit:
	addi $t6, $t6, 4
	move $t8, $t6
	move $t4, $t5
	move $ra, $t5
	addi $ra, $ra, 1
	addi $t9, $t9, 4
	addi $at, $at, 1
	subi $s5, $s5, 1
	la $t6, cache
	li $t5, 0
	j begin2

done:
	li $v0, 10
	syscall
