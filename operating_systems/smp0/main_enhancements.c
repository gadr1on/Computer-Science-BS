/*
About this program:
- This program counts words.
- The specific words that will be counted are passed in as command-line
  arguments.
- The program reads words (one word per line) from standard input until EOF or
  an input line starting with a dot '.'
- The program prints out a summary of the number of times each word has
  appeared.
- Various command-line options alter the behavior of the program.

E.g., count the number of times 'cat', 'nap' or 'dog' appears.
> ./main cat nap dog
Given input:
 cat
 .
Expected output:
 Looking for 3 words
 Result:
 cat:1
 nap:0
 dog:0
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "smp0_tests.h"

/* Structures */
typedef struct {
  char *word;
  int counter;
} WordCountEntry;

//definition of the function process_stream()
int process_stream(WordCountEntry entries[], int entry_count) {
  //define the variables
  short line_count = 0;
  char buffer[30];

  while (fgets(buffer, sizeof(buffer), stdin)) {
    //check if the word has '.'
    if (*buffer == '.') break;

    //find the length of the word
    size_t len = strlen(buffer);

    //check for the new line
    if (buffer[len - 1] == '\n') buffer[--len] = 0; // assign 0

    /* Compare against each entry */
    if (!strcmp(entries[line_count].word, buffer)) entries[line_count].counter++; //increment the count

    if (++line_count == entry_count) break;
  }
//return the line_count
return line_count;
}

//definition of the function print_result()
//it uses fprintf to print the result
void print_result(WordCountEntry entries[], int entry_count) {

  fprintf(stdout, "\nResult:\n");

  for (int i = 0; i < entry_count; i++) {
    fprintf("%s:%d\n", entries[i].word, entries[i].counter);
  }
}

//definition of the function printHelp()
//it uses fprintf to print the Help
void printHelp(const char *name) {
  fprintf(stderr, "usage: %s [-h] [-f FILENAME] <word1> ... <wordN>\n", name);
}

//definition of the function main()
int main(int argc, char **argv) {

  const char *prog_name = *argv;

  //Add support for matching arbitrary number of words, not just 5.
  //WordCountEntry entries[argc];
  WordCountEntry entries[5];
  int entryCount = 0;

  /* Entry point for the testrunner program */
  if (argc > 1 && !strcmp(argv[1], "-test")) {
    run_smp0_tests(argc - 1, argv + 1);
    return EXIT_SUCCESS;
  }

  for (int i = 1; i < argc; i++) {
    if (*argv[i] == '-') {
      switch (argv[i][1]) {
        // h to print help message.
        // printHelp()
        case 'h':
          printHelp(prog_name);
          break;
        // f to open file
        // freopen()
        case 'f':
          freopen((*argv)[2], "w", stdout);
          break;
        default:
        //error and help messages are sent to the standard error stream (stderr).
          fprintf(stderr, "%s: Invalid option %s. Use -h for help.\n", prog_name, *argv);
        }
      } else {
        entries[entryCount].word = argv[i];
        entries[entryCount++].counter = 0;
      }
    }

  if (!entryCount) {
  //error and help messages are sent to the standard error stream (stderr).
    fprintf(stderr, "%s: Please supply at least one word. Use -h for help.\n", prog_name);
    return EXIT_FAILURE;
  }

  if (entryCount == 1) {
    fprintf(stdout, "Looking for a single word\n");
  } else {
    fprintf(stdout, "Looking for %d words\n", entryCount);
  }

  process_stream(entries, entryCount);
  print_result(entries, entryCount);

  return EXIT_SUCCESS;
}
