#include "vm.h"
#include "vmlib.h"

/**
 * The malloc() function allocates size bytes and returns a pointer
 * to the allocated memory (payload). The memory is not initialized.
 * If size is 0, or if no available heap memory could be allocated,
 * malloc returns NULL.
 */
int convertSize(size_t size)
{
    //round down to nearest multiple of 8
    size = size - size%4;
    return size;
}
void *set_blocks(char* bestFit, size_t realSize, size_t nextSize, int prev_availability)
{
    struct block_header *toReturn = (struct block_header*) bestFit;
    toReturn->size_status = realSize;
    toReturn->size_status |= prev_availability;
    toReturn->size_status |= 0b1;

    struct block_header *next = (struct block_header*)(bestFit + realSize);
    if(nextSize != 0 && next->size_status != 1)
    {
        next->size_status = nextSize;
        next->size_status &= ~0b01;
    }
    if(next->size_status != 1)
        next->size_status |= 0b10;
    return toReturn + 1;
}
void *vmalloc(size_t size)
{
    if(size == 0)
    {
        return NULL;
    }
    /* TODO: PA 5 */
    //
    size_t realSize = size + 4;
    //round up to nearest multiple of 8
    if(realSize%8 != 0)
        realSize = realSize + (8 - (realSize%8));
    //extern struct block_header *heapstart;
    //^global pointer to start of heap
    char *iterator = (char*) heapstart;
    char *bestFit = iterator;
    
    int converted_size;
    int bestFit_size = 0;
    int bestFitBusy = 0;
    while(((struct block_header*)iterator)->size_status != 1)
    {
        converted_size = convertSize(((struct block_header*)iterator)->size_status);
        if(((struct block_header*)iterator)->size_status % 2 == 1)//odd number implies allocated block
        {
            iterator += converted_size;
            continue;
        }
        bestFitBusy = ((struct block_header*)bestFit)->size_status % 2;
        bestFit_size = convertSize(((struct block_header*)bestFit)->size_status);
        if(converted_size == realSize)
        {
            return set_blocks(iterator, realSize, 0, ((struct block_header*)iterator)->size_status&0b10);
        }//bestFit may point to the first block
        else if(converted_size >= realSize && ((converted_size < bestFit_size) || (bestFit_size < realSize) || (bestFitBusy)))
        {
            bestFit = iterator;
        }
        iterator += converted_size;
        //aaa
    }
    bestFitBusy = ((struct block_header*)bestFit)->size_status % 2;
    bestFit_size = convertSize(((struct block_header*)bestFit)->size_status);
    if(bestFit_size < realSize || bestFitBusy)
    {
        return NULL;
    }
    //bestFit is now the correct block afaik. Now we just need to set up the headers and footers correctly.
    //
    //size_status includes the size of the header.
    struct block_header *toReturn = (struct block_header*)bestFit;
    //we have and want to keep previous availability in the size status. It's the second bit.
    return set_blocks(bestFit, realSize, bestFit_size - realSize, (toReturn->size_status)&0b10);
}

