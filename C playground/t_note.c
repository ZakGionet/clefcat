//
// Created by zak-gionet on 12/8/25.
//

#include "t_note.h"

#include <stdlib.h>
#include <string.h>

note *init_note(char *name, char *symbol, accents accent) {
    note *n = malloc(sizeof(note));
    if (!n) {return NULL;}

    n->name = malloc(strlen(name) + 1);
    if (!n->name) {
        free(n);
        return NULL;
    }
    strcpy(n->name, name);

    n->symbol = malloc(strlen(symbol) + 1);
    if (!n->symbol) {
        free(n);
        return NULL;
    }
    strcpy(n->symbol, symbol);

    n->accent = accent;
    n->equiv = NULL;

    return n;
}

void add_equiv(note *note_1, note *note_2) {
    note_1->equiv = note_2;
}
