//
// Created by zak-gionet on 12/8/25.
//

#include "t_note.h"

#include <stdlib.h>
#include <string.h>

t_note *t_note_init(char *name, char *symbol, accents accent) {
    t_note *n = malloc(sizeof(t_note));
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
    n->next_note = NULL;
    n->prev_note = NULL;

    return n;
}

int t_note_add_equiv(t_note *note_1, t_note *note_2) {
    if (!note_1 || !note_2) {
        return 0;
    }
    note_1->equiv = note_2;
    note_2->equiv = note_1;
    return 1;
}

int t_note_link_next(t_note *note_1, t_note *note_2) {
    if (!note_1 || !note_2) {
        return 0;
    }
    note_1->next_note = note_2;
    note_2->prev_note = note_1;
    return 1;
}

// Define the actual storage for the pointers
#define X(varname, textname, symbol, accent) t_note *varname = NULL;
NOTES_LIST
#undef X

// Initialize all variables by calling t_note_init()
void init_all_notes(void) {
    #define X(varname, textname, symbol, accent) \
        varname = t_note_init(textname, symbol, accent);
    NOTES_LIST
    #undef X
}