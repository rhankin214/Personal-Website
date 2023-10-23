#include "vmlib.h"
#include <assert.h>
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
int main()
{
    vminit(0x5000);

    assert(vmalloc(1256));
    assert(vmalloc(5928));
    assert(vmalloc(9040));
    vminfo();
    
    assert(vmalloc(4220));
    assert(vmalloc(1) == NULL);

    vmdestroy();
}
