/**
 * CSE 30: Computer Organization & Systems Programming
 * Winter Quarter, 2023
 * Programming Assignment II
 * 
 * cipher.c
 * This program implements a text-based cipher that takes a certain stride `n`,
 * and reverses the input text within groups of every `n` characters. If the
 * last segment is not `n` characters long, it is not reversed.
 * 
 * E.g.
 *   >> in  = "ABCABCABCABC"; stride = 3;
 *   << out = "CBACBACBACBA" 
 *   >> in  = "This is a string."; stride = 2;
 *   << out = "hTsii  s atsirgn."
 *   >> in  = "12345"; stride = 10;
 *   << out = "12345"
 * 
 * Author: Jerry Yu
 * January 2023
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFSZ 256

void encrypt(char *text, int stride)
{
    char temp;
    for(int strdCnt = 0; (strdCnt + 1) * stride <= strlen(text); strdCnt++)
        for(int i = 0; i < stride/2; i++)
        {
            temp = text[strdCnt * stride + i];
            text[strdCnt * stride + i] = text[(strdCnt+1)*stride - 1 - i];
            text[(strdCnt+1) * stride - 1 - i] = temp;
        }
    // TODO: implement this function
}

char *get_input(const char *prompt, char *buf, size_t bufsz)
{
    printf("%s: ", prompt);
    // flush the stdout buffer so the prompt is printed immediately.
    fflush(stdout);

    if (!fgets(buf, bufsz, stdin)) {
        fprintf(stderr, "Error: failed to read input.\n");
        exit(EXIT_FAILURE);
    }
    return buf;
}

int get_stride(char *buf)
{
    return atoi(get_input("stride", buf, BUFSZ));
}

void get_plain_text(char *buf)
{
    get_input("input", buf, BUFSZ);

    // Strip the trailing newline. If no trailing newline was found, 
    // that means the input exceeded the buffer size and was truncated.
    if (buf[strlen(buf) - 1] == '\n') {
        buf[strlen(buf) - 1] = '\0';
    } else {
        fprintf(stderr, "Error: input string cannot exceed %d characters.\n", BUFSZ);
        exit(EXIT_FAILURE);
    }
}

int main()
{
    char buf[BUFSZ];

    int stride = get_stride(buf);

    if(stride < 2 || stride > 64)
    {
        fprintf(stderr, "Error: segment size must be between 2 and 64.\n");
        return EXIT_FAILURE;
    }

    get_plain_text(buf);

    encrypt(buf, stride);

    fprintf(stdout, "%s\n", buf);

    return EXIT_SUCCESS;
}
