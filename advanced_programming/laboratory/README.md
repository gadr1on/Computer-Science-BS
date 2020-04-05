## Lab-1: Java as Structured Programming
Compiling and executing Java exercises using loop statements and arrays.

1. Write an application that inputs three integers from the user and displays the sum, average, product, smallest and largest of the numbers. [Note: The calculation of the average in this exercise should result in an integer representation of the average. So if the sum of the values is 7, the average should be 2, not 2.3333….] Sample output2. Write an application that finds two neighboring numbers in an array with the smallest distance to each other. The program should return the index of the first number. For example given the sequence of numbers in array A={ 4 8 6 1 2 9 4} the minimum distance is 1 (between 1 and 2). The program should return the index 3 (of number 1). Hint: Use Math.abs() to calculate the distance of two numbers.

3. Create a Java program with the following characteristics:
    1. Prompts for and reads in the sales for each of 5 vendor in a company. It then prints out the id and amount of sales for each vendor and the total sales.
    2. Compute and print the average sale.
    3. Find and print the maximum sale. Print both the id of the vendor with the min sale and the amount of the sale, e.g., "Vendor 3 had the lowest sale with $4500."
    4. Do the same with the minimum value.
    5. After the list, sum, average, max, and min have been printed, ask the user to enter a value. Then print the id of each vendor who exceeded that amount, and the amount of their sales. Also print the total number of vendor whose sales exceeded the value entered.
    6. Modify your program so that the ids run from 1–5 instead of 0–4.
    7. Instead of always reading in 5 sales amounts, at the beginning ask the user for the number of sales people and then create an array that is just the right size.

4. Write a program that grades arithmetic quizzes as follows:
    1. Ask the user how many questions are in the quiz.
    2. Ask the user to enter the key (that is, the correct answers). There should be one answer for each question in the quiz, and each answer should be an integer. For example the key for a 10-question quiz, can be (single line): 34 7 13 100 81 3 9 10 321 12. Hint, store the key in an array.
    3. Ask the user to enter the answers for the quiz to be graded. As for the key, these can be entered on a single line. Note that these answers do not need to be stored; each answer can simply be compared to the key as it is entered.
    4. When the user has entered all of the answers to be graded, print the number correct and the percent correct.
