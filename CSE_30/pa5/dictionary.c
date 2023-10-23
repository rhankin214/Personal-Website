/**
 * pa4/dictionary.c
 *
 * The dictionary linked list interface.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "dictionary.h"

/**
 * Allocate a new dictionary entry with the given word and its definition.
 * The length of the word should not exceed MAXWORDLEN (defined above).
 * NOTE: Do NOT directly assign the `word` and `definition` strings to the struct!
 * Copy them into the struct instead (and allocate buffer where needed).
 * Use strncpy instead of strcpy?
 */
struct dict_entry *new_entry(const char *word, const char *definition)
{
    struct dict_entry *toReturn = malloc(sizeof(struct dict_entry));
    strncpy(toReturn->word, word, MAXWORDLEN);//this works
    toReturn->word[MAXWORDLEN] = '\0';
    toReturn->definition = malloc(sizeof(char)*(strlen(definition) + 1));

    for(int i = 0; i < strlen(definition); i++) //not working 
        toReturn->definition[i] = definition[i];
    
    toReturn->definition[strlen(definition)] = '\0';

    toReturn->next = NULL;
    return toReturn; 
}

void free_entry(struct dict_entry *entry)
{
    free(entry->definition);
    entry->next = NULL;
    free(entry);
}
void toLowerCase(char *string)
{
    for(int i = 0; i < strlen(string); i++)
    {
        if(string[i] < 'a' && string[i] != '_')
        {
            string[i] += 32;
        }
    }
}
//returns positive if string1 is before string2 alphabetically
int compare_words(char *string1, char *string2)
{
    //copy1
    char *copy1 = malloc(sizeof(char)*MAXWORDLEN);
    strncpy(copy1, string1, MAXWORDLEN);
    toLowerCase(copy1);

    char *copy2 = malloc(sizeof(char)*MAXWORDLEN);
    strncpy(copy2, string2, MAXWORDLEN);
    toLowerCase(copy2);

    for(int i = 0; i < strlen(string1) && i < strlen(string2); i++)
    {
        if(copy1[i] - copy2[i] < 0)
        {
            free(copy1);
            free(copy2);
            return 1;
        }
        else if(copy1[i] - copy2[i] > 0)
        {
            free(copy1);
            free(copy2);
            return -1;
        }
    }
    if(strlen(copy1) < strlen(copy2))
    {
        free(copy1);
        free(copy2);
        return 1;
    }
    else if(strlen(copy1) > strlen(copy2))
    {
        free(copy1);
        free(copy2);
        return -1;
    }
    free(copy1);
    free(copy2);
    return 0;
}
/**
 * Insert a dictionary entry into a dictionary while keeping the dictionary
 * in sorted order. You may assume there won't be any duplicates.
 */
void dict_insert_sorted(struct dictionary *dict, struct dict_entry *entry)
{
    //if dictionary is empty, insert at head
    if(dict->size == 0)
    {
        dict->list = entry;
        dict->size++;
        return;
    }
    //behavior for comparing to only node in the list
    if(dict->size == 1)
    {
        if(compare_words(entry->word, dict->list->word) > 0)//this doesn't work. Probably a strcmp problem. Make my own.
        {
            entry->next = dict->list;
            dict->list = entry;
        }
        else
        {
            dict->list->next = entry;
        }
        dict->size++;
        return;
    }
    //otherwise, iterate through array until finding the entries to insert between
    //correct place will have compare_strings positive for the one ahead and negative for the one before.
    struct dict_entry *curr = dict->list;
    while(curr->next != NULL)
    {
        if(compare_words(entry->word, curr->word) < 0 && compare_words(entry->word, curr->next->word) > 0)
        {
            entry->next = curr->next;
            curr->next = entry;
            dict->size++;
            return;
        }
        curr = curr->next;
    }
    //behavior for when curr->next does = null, i.e. curr is the last node in the list. 
    //This only happens if entry would be last alphabetically
    curr->next = entry;
    dict->size++;
    return;
}

void dict_remove_word(struct dictionary *dict, const char *word)
{
    struct dict_entry *curr = dict->list;
    if(compare_words(curr->word, (char*)word) == 0)
    {
        dict->list = curr->next;
        free_entry(curr);
        return;
    }
    struct dict_entry *prev;
    while(curr != NULL)
    {
        if(compare_words(curr->word, (char*)word) == 0)
        {
            prev->next = curr->next;
            free_entry(curr);
            return;
        }
        prev = curr;
        curr = curr->next;
    } 
}

struct dict_entry *dict_lookup(struct dictionary *dict, const char *word)
{
    struct dict_entry *curr = dict->list;
    while(curr != NULL)
    {
        if(compare_words(curr->word, (char*)word) == 0)
            return curr;
        curr = curr->next;
    }
    return NULL;
}

void free_dictionary(struct dictionary *dict)
{
    struct dict_entry *curr = dict->list;
    while(curr != NULL)
    {
        dict->list = curr->next;
        free_entry(curr);
        curr = dict->list;
    }
    free(dict->list);
    dict->list = NULL;
    free(dict);
    dict = NULL;
}

void print_dictionary(struct dictionary *dict)
{
    struct dict_entry *curr = dict->list;
    while(curr != NULL)
    {
        print_word(curr);
        curr = curr->next;
    }
    return; // placeholder
}

void print_word_list(struct dictionary *dict)
{
    struct dict_entry *curr = dict->list;
    while(curr != NULL)
    {
        printf("%s\n", curr->word);
        curr = curr->next;
    }
}

void print_word(struct dict_entry *entry)
{
    printf("%s\n  %s\n", entry->word, entry->definition);
    return;
}

struct dictionary *load_dictionary(const char *filename)
{
    FILE *fp;
    char *line = NULL;
    size_t len = 0;
    ssize_t nread;

    struct dictionary *dict;
    if (!(dict = malloc(sizeof(*dict)))) {
        perror("malloc");
        return NULL;
    }

    dict->size = 0;
    dict->list = NULL;

    if (!filename) {
        return dict;
    }

    if (!(fp = fopen(filename, "r"))) {
        perror("fopen");
        return NULL;
    }

    while ((nread = getline(&line, &len, fp)) != -1) {
        char *word = strtok(line, ":");
        char *definition = strtok(NULL, "\n");
        while (*definition == ' ')
            definition++;
        struct dict_entry *entry = new_entry(word, definition);
        dict_insert_sorted(dict, entry);
    }
    free(line);
    fclose(fp);
    return dict;
}
