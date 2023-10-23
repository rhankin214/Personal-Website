#include "vm.h"
#include "vmlib.h"

/**
 * The vmfree() function frees the memory space pointed to by ptr,
 * which must have been returned by a previous call to vmmalloc().
 * Otherwise, or if free(ptr) has already been called before,
 * undefined behavior occurs.
 * If ptr is NULL, no operation is performed.
 * vmfree() asserts that ptr is 8-byte aligned.
 */
int convertSizes(size_t size)
{
    //round down to nearest multiple of 8
    size = size - size%4;
    return size;
}
void vmfree(void *ptr)
{
    if(ptr == NULL)
    {
        return;
    }
    char* iterator = ptr - sizeof(struct block_header);
    ((struct block_header*)iterator)->size_status &= ~0b1;
    int totalSize = convertSizes(((struct block_header*)iterator)->size_status);
    iterator += totalSize;
    //keep going until we find an odd block
    while(((struct block_header*)iterator)->size_status % 2 != 1)
    {
        totalSize += convertSizes(((struct block_header*)iterator)->size_status);
        iterator += convertSizes(((struct block_header*)iterator)->size_status);
    }
    //reset our iterator
    iterator = ptr;
    char* footerLoc;
    /* TODO: PA 6 */
    while((((struct block_header*)iterator)->size_status&0b10) == 0)
    {
        footerLoc = iterator - sizeof(struct block_footer);
        iterator -= ((struct block_footer*)footerLoc)->size;
        totalSize += convertSizes(((struct block_header*)iterator)->size_status);
    }

    ((struct block_header*)iterator)->size_status = totalSize;
    ((struct block_header*)iterator)->size_status |= 0b10;
}
