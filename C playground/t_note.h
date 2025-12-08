//
// Created by zak-gionet on 12/8/25.
//
#include <stdbool.h>
#include <stddef.h>

#define MAX_NAME_LENGTH 30
#define MAX_SYMBOL_LENGTH 3

#ifndef C_PLAYGROUND_T_NOTE_H
#define C_PLAYGROUND_T_NOTE_H

typedef struct note note;
typedef enum accents accents;
typedef enum scale_types scale_types;

enum accents {FLAT, NATURAL, SHARP};
enum scale_types {MAJOR, NATURAL_MINOR, HARMONIC_MINOR, MELODIC_MINOR};


struct note {
    char *name;
    char *symbol;
    accents accent;
    note *equiv;
};

note *init_note(char name[MAX_NAME_LENGTH], char symbol[MAX_SYMBOL_LENGTH], accents accent);
void add_equiv(note *note_1, note *note_2);

#endif //C_PLAYGROUND_T_NOTE_H