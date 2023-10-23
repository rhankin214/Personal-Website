/**
 * CSE 30: Computer Organization & Systems Programming
 * Winter Quarter, 2023
 * Programming Assignment III
 *
 * wordle.c
 * This program implements a command-line version of the popular wordle game.
 * (https://en.wikipedia.org/wiki/Wordle) In this first part, we implement a
 * version of the game with 5-letter words and 6 attempts in total. In the
 * next part, we will extend the game to have configurable word lengths and
 * number of attempts.
 *
 * Author: Jerry Yu
 * January 2023
 */

#include <stdio.h>
#include <string.h>
#include "wordle.h"

int main(int argc, char **argv)
{
    /* 2D array to hold game data */
    char attempts[NATTEMPTS][WORDBUFSZ];
    /* The target word */
    char answer[WORDBUFSZ];

    print_title(WORDLEN, NATTEMPTS);

    /* 
     * TODO:
     * Generate the target word.
     */
    if(argc != 2)
    {
        keygen(DEFAULTDICT, WORDLEN, answer);
    }
    else
    {
        keygen(argv[1], WORDLEN, answer);
    }
    /* wipe out the garbadge values in the game data. */
    for (int i = 0; i < NATTEMPTS; i++) {
        for (int j = 0; j < WORDBUFSZ; j++) {
            attempts[i][j] = '\0';
        }
    }

    /* Start the wordle game */
    int n;
    if ((n = wordle(answer, NATTEMPTS, attempts))) {
        print_success_message(n);
    } else {
        print_failure_message(answer);
    }

    return 0;
}
