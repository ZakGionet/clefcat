//
// Created by zak-gionet on 12/5/25.
//

#include "scale_finder.h"

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

scale_interval *init_scale_interval(scale_types type) {
    scale_interval *si = malloc(sizeof(scale_interval));
    if (!si) {return NULL;}
    si->scale_type = type;

    switch (type) {
        case MAJOR:
            memcpy(si->interval_ascending, major_intervals, sizeof(int) * 7);
            memcpy(si->interval_descending, major_intervals, sizeof(int) * 7);
            break;
        case NATURAL:
            memcpy(si->interval_ascending, natural_minor_intervals, sizeof(int) * 7);
            memcpy(si->interval_descending, natural_minor_intervals, sizeof(int) * 7);
            break;
        case HARMONIC_MINOR:
            memcpy(si->interval_ascending, harmonic_minor_intervals, sizeof(int) * 7);
            memcpy(si->interval_descending, harmonic_minor_intervals, sizeof(int) * 7);
            break;
        case MELODIC_MINOR:
            memcpy(si->interval_ascending, melodic_ascending_minor_intervals, sizeof(int) * 7);
            memcpy(si->interval_descending, melodic_descending_minor_intervals, sizeof(int) * 7);
            break;

    }
    return si;
}

scale *init_scale(char *name, scale_types scale_type) {
    scale *s = malloc(sizeof(scale));
    if (!s) {return NULL;}

    s->name = malloc(strlen(name) + 1);
    if (!s->name) {
        free(s);
        return NULL;
    }
    strcpy(s->name, name);

    for (int i = 0; i < 7; i++) {
        s->notes_ascending[i].name = NULL;
        s->notes_ascending[i].symbol = NULL;
        s->notes_ascending[i].accent = NATURAL;
        s->notes_ascending[i].equiv = NULL;

        s->notes_descending[i].name = NULL;
        s->notes_descending[i].symbol = NULL;
        s->notes_descending[i].accent = NATURAL;
        s->notes_descending[i].equiv = NULL;
    }

    s->type = scale_type;

    return s;
}


void populate_scale(scale *scale, note *start_note, twelve_notes *twelve_notes, scale_interval *scale_interval) {
    scale->notes_ascending[0] = *start_note;


    for (int i = 1; i < 7; i++) {
        scale->notes_ascending[i] =
    }
}
void init_sharp_twelve_notes(note *start_note);
void init_flat_twelve_notes(note *start_note);


twelve_notes all_sharp = {{A_flat, }};
