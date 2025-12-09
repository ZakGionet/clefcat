//
// Created by zak-gionet on 12/8/25.
//


#ifndef C_PLAYGROUND_T_NOTE_H
#define C_PLAYGROUND_T_NOTE_H

#include <stdbool.h>
#include <stddef.h>
#include "notes_list.h"

#define MAX_NAME_LENGTH 30
#define MAX_SYMBOL_LENGTH 3

typedef struct t_note t_note;
typedef enum accents accents;
typedef enum scale_types scale_types;

enum accents {FLAT, NATURAL, SHARP};
enum scale_types {MAJOR, NATURAL_MINOR, HARMONIC_MINOR, MELODIC_MINOR};


struct t_note {
    char *name;
    char *symbol;
    accents accent;
    t_note *equiv;
    t_note *next_note;
    t_note *prev_note;
};

// Declare 21 extern pointers:
// extern t_note *A_NATURAL;
// extern t_note *B_FLAT;
// ...
#define X(varname, textname, symbol, accent) extern t_note *varname;
NOTES_LIST
#undef X

t_note *t_note_init(char *name, char *symbol, accents accent);

int t_note_add_equiv(t_note *note_1, t_note *note_2);

int t_note_link_next(t_note *note_1, t_note *note_2);

// Function that initializes all of them
void init_all_notes(void);

#endif //C_PLAYGROUND_T_NOTE_H