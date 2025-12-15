//
// Created by zak-gionet on 12/8/25.
//


#ifndef C_PLAYGROUND_T_NOTE_H
#define C_PLAYGROUND_T_NOTE_H

#include <stdbool.h>
#include <stddef.h>
#include "notes_list.h"
#include "t_interval.h"

#define MAX_NAME_LENGTH 30
#define MAX_SYMBOL_LENGTH 3
#define MAX_NOTE_COUNT 1000

typedef struct t_note t_note;
typedef enum accents accents;
typedef enum scale_types scale_types;

enum accents {FLAT, NATURAL, SHARP};
enum scale_types {MAJOR, NATURAL_MINOR, HARMONIC_MINOR, MELODIC_MINOR};


struct t_note {
    char *name;
    char *symbol;
    accents accent;
    interval_type interval;
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

t_note *t_note_init(const char *name, const char *symbol, const accents accent, t_note **note_addr, int *note_index);

t_note *t_note_create_copy(t_note *note, t_note **note_addr, int *note_idx);

int t_note_add_equiv(t_note *note_1, t_note *note_2);

int t_note_link_next(t_note *note_1, t_note *note_2);

void t_note_set_interval(t_note *t_note, interval_type interval_type);

// Function that initializes all of them
void t_note_init_starting_notes(t_note **note_addr);

void t_note_destroy_all(t_note **note_addr, int *note_index);



#endif //C_PLAYGROUND_T_NOTE_H