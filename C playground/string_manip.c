//
// Created by user on 12/8/2025.
//

#include "string_manip.h"

#include <string.h>

char *concat_strings(char *s1, char *s2, char join) {

    char *output = "";

    strcpy(output, s1);
    size_t s1_length = strlen(s1);
    output[s1_length] = join;
    s1_length++;


    size_t s2_length = strlen(s2);

    for (size_t i = s1_length; i < s1_length + s2_length; i++) {
        output[i] = s2[i - s1_length];
    }
    return output;
}
