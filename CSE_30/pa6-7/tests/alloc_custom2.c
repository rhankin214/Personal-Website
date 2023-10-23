#include "vmlib.h"
#include <assert.h>
#include <stdint.h>
#include <stdlib.h>

int main()
{
    vmload("../dumps/ref_image5");
    vminfo();

    void *ptr = vmalloc(250);
    void *ptr2 = vmalloc(250);
    void *ptr3 = vmalloc(150);
    void *ptr4 = vmalloc(150);
    vminfo();
    void *ptr5 = vmalloc(50);
    void *ptr6 = vmalloc(50);
    assert(ptr != NULL && ptr2 != NULL && ptr3 != NULL && ptr4 != NULL && ptr5 != NULL && ptr6 != NULL);

    // check pointer offsets
    assert((uint32_t)(ptr2-ptr) == 760);
    assert((uint32_t)(ptr3-ptr) == 256);
    assert((uint32_t)(ptr4-ptr) == 1016);
    assert((uint32_t)(ptr5-ptr) == 416);
    assert((uint32_t)(ptr6-ptr) == 1176);

    vmdestroy();
}

