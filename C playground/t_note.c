//
// Created by zak-gionet on 12/8/25.
//

#include "t_note.h"

#include <stdlib.h>
#include <string.h>

t_note *t_note_init(const char *name, const char *symbol, const accents accent, t_note **note_addr, int *note_index) {
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

    if (note_addr && note_index) {
        note_addr[*note_index] = n;
        *note_index += 1;
    }
    return n;
}

void t_note_set_interval(t_note *t_note, int nb_semitones) {
    interval_type interval = interval_type_get_interval(nb_semitones);

    t_note->
}

void t_note_destroy(t_note *t_note) {
    if (!t_note) {
        return;
    }
    if (t_note->name) {
        free(t_note->name);
    }
    if (t_note->symbol) {
        free(t_note->symbol);
    }
    free(t_note);
}

t_note *t_note_create_copy(t_note *note, t_note **note_addr, int *note_idx) {
    // return t_note_init(note->name, note->symbol, note->accent, note_addr, note_idx);
    t_note *new_note = t_note_init(note->name, note->symbol, note->accent, note_addr, note_idx);

    if (note->equiv) {
        new_note->equiv = note->equiv;
    }
    return new_note;
    // t_note *new_note = malloc(sizeof(t_note));
    // if (!new_note) {
    //     return NULL;
    // }
    //
    // new_note = t_note_init(note->name, note->symbol, note->accent, note_addr, note_idx);
    // if (note->next_note) {
    //     t_note_
    // }
    // if ()
}

void t_note_set_interval(t_note *t_note, interval_type interval_type) {
    t_note->interval = interval_type;
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


// Initialize all variables by calling t_note_init()
void t_note_init_starting_notes(t_note **note_addr) {
    #define X(varname, textname, symbol, accent) \
        *note_addr = t_note_init(textname, symbol, accent, NULL, NULL); note_addr++;
        NOTES_LIST
    #undef X
}

void t_note_destroy_all(t_note **note_addr, int *note_index) {
    for (int i = 0; i < *note_index; i++) {
        if (note_addr[i]) {
            t_note_destroy(note_addr[i]);
        }
    }
    *note_index = 0;
}