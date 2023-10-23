#include <stdio.h>

int char2ascii(char c){
    int ascii = c;
    return ascii;
}

int main(){
    int a = 10;
    int b = 3;
    double c = 2.5;
    
    printf("a = %d\n", a);
    printf("b = %d\n", b);
    printf("c = %.1f\n", c);
    printf("a / b = %d\n", a/b);
    printf("a %% b = %d\n", a%b);
    printf("a / c = %.1f\n", a/c);
    printf("a / (int) c = %d\n", a /(int)c);
    printf("(int) (a / c) = %d\n", (int)(a/c));

    printf("ASCII of A = %d\n", char2ascii('A'));
    printf("ASCII of a = %d\n", char2ascii('a'));
    printf("ASCII of 0 = %d\n", char2ascii('0'));

    return 0;
}
