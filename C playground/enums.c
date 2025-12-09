//
// Created by user on 12/8/2025.
//

#include "enums.h"

// 2. Generate the enum
enum scale_types {
    #define X(id, str) id,
        SCALE_TYPE_LIST
    #undef X
};

// 3. Generate the string conversion function
char* scale_type_to_string(enum scale_types s) {
    switch (s) {
        #define X(id, str) case id: return str;
            SCALE_TYPE_LIST
        #undef X
    }
    return "UNKNOWN_SCALE_TYPE";
}



// 2. Generate the enum
enum accents {
    #define X(id, str) id,
        ACCENTS_LIST
    #undef X
};

// 3. String conversion function
char* accents_to_string(enum accents a) {
    switch (a) {
        #define X(id, str) case id: return str;
            ACCENTS_LIST
        #undef X
    }
    return "Unknown accent";
}