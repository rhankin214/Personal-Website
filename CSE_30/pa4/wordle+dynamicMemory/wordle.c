#include "wordle.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
/**
 * Play the wordle game.
 *
 * key          The answer word. Should be lowercase only.
 * nattempts    The total number of attempts allowed.
 * attempts     An array of string buffers to keep track of each attempt.
 * return       Number of guesses the player took to guess the right word,
 *              return 0 if player failed to guess the word.
 */
int wordle(const char *key, int nattempts, char **attempts)
{
     /*
     * TODO:
     * Implement this function. Feel free to create any
     * helper functions you need. But do NOT change the
     * parameters of this function.
     */
     int attemptsTaken = 0;
     int correctGuesses = 0;
     char *updatingString = malloc(sizeof(char) * (strlen(key)+1));
     
     for(int i = 0; i < strlen(key); i++)
        updatingString[i] = '_';
     updatingString[strlen(key)] = '\0';
     while(attemptsTaken <= nattempts)
     {
         printf("%s\r", updatingString);
         get_input(attempts[attemptsTaken], strlen(key));
         int correctLetters = 0;
         for(int i = 0; i < strlen(attempts[attemptsTaken]); i++)
         {
            if(attempts[attemptsTaken][i] == key[i])
               correctLetters += 1;

            if(correctLetters == strlen(key))
            {
               free(updatingString);
               updatingString = NULL;
               return attemptsTaken +1;

            }
         }
         for(int i = 0; i < strlen(attempts[attemptsTaken]); i++)
         {
            for(int j = 0; j < strlen(key); j++)
            {
               if(attempts[attemptsTaken][i] == key[j] && updatingString[j] == '_')
               {
                  updatingString[j] = key[j];
                  correctGuesses++;
               }
            }
         }
         attemptsTaken++;
         
         if(attemptsTaken >= nattempts)
         {
            free(updatingString);
            updatingString = NULL;
            return 0;
         }
     }
     return 0; // placeholder
}
