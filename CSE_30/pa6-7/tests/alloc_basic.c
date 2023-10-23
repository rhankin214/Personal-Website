#include "vmlib.h"
#include <assert.h>
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
int main()
{
    vminit(4096);

    void *ptr = vmalloc(4);

    assert(ptr != NULL);
    printf("%d", ((uint32_t)ptr) % 8);
    assert((uint32_t)ptr % 8 == 0);

    vmdestroy();
}
