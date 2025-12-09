//
// Created by user on 12/8/2025.
//

#include "t_scale.h"

#include "enums.h"
#include "string_manip.h"
#include <stdlib.h>
#include <string.h>


int copy_int_array(int *source, int *dest, size_t length) {
    if (!source || !dest) {
        return 0;
    }
    for (size_t i = 0; i < length; i++) {
        dest[i] = source[i];
    }

    return 1;
}

t_scale *t_scale_init(t_note *tonic, scale_types type) {
    t_scale *s = malloc(sizeof(t_scale));
    if (!s) {
        return NULL;
    }

    // SET name
    s->name = concat_strings(tonic->symbol, scale_type_to_string(type), ' ');

    // SET type
    s->type = type;

    // SET ascending - descending
    t_twelve_notes *t_tn = t_twelve_notes_init(tonic);
    t_scale_interval *t_si = t_scale_interval_init(type);

    s->notes_ascending[0] = *tonic;

    for (int i = 1; i < 7; i++) {

        int ascending_interval = t_si->interval_ascending[i - 1];
        s->notes_ascending[i] = t_tn->notes[ascending_interval];
        // NOTE: There could be a case where the next note check doesn't work, but I can't
        // Think of one right now.

        char prev_note_symbol = s->notes_ascending[i - 1].symbol[0];
        char current_note_symbol = s->notes_ascending[i].symbol[0];

        if (prev_note_symbol + 1 != current_note_symbol) {
            s->notes_ascending[i] = *s->notes_ascending[i].equiv;
        }

        int descending_interval = t_si->interval_descending[i - 1];
        s->notes_descending[i] = t_tn->notes[descending_interval];

        prev_note_symbol = s->notes_descending[i - 1].symbol[0];
        current_note_symbol = s->notes_descending[i].symbol[0];

        if (prev_note_symbol + 1 != current_note_symbol) {
            s->notes_descending[i] = *s->notes_descending[i].equiv;
        }
    }

    t_twelve_notes_destroy(t_tn);

    return s;
}

void t_scale_populate(t_scale *scale);

t_twelve_notes *t_twelve_notes_init(t_note *tonic) {
    t_twelve_notes *t_tn = malloc(sizeof(t_twelve_notes));
    if (!t_tn) {
        return NULL;
    }
    t_tn->notes[0] = *tonic;

    t_note *next_note = tonic->next_note;
    if (!next_note) {
        return NULL;
    }
    for (int i = 1; i < 12; i++) {
        if (next_note->accent != tonic->accent || NATURAL) {
            next_note = next_note->equiv;
        }
        t_tn->notes[i] = *next_note;
        next_note = next_note->next_note;
        if (!next_note) {
            return NULL;
        }
    }
    return t_tn;
}

void t_twelve_notes_destroy(t_twelve_notes *t_twelve_notes) {
    free(t_twelve_notes);
}

void t_scale_interval_destroy(t_scale_interval *t_scale_interval) {
    free(t_scale_interval);
}

t_scale_interval *t_scale_interval_init(scale_types type) {
    t_scale_interval *si = malloc(sizeof(t_scale_interval));
    if (!si) {return NULL;}

    si->scale_type = type;

    switch (type) {
        case MAJOR:
            copy_int_array(major_intervals, si->interval_ascending, 7);
            copy_int_array(major_intervals, si->interval_descending, 7);
            break;
        case NATURAL:
            copy_int_array(natural_minor_intervals, si->interval_ascending, 7);
            copy_int_array(natural_minor_intervals, si->interval_descending, 7);
            break;
        case HARMONIC_MINOR:
            copy_int_array(harmonic_minor_intervals, si->interval_ascending, 7);
            copy_int_array(harmonic_minor_intervals, si->interval_descending, 7);
            break;
        case MELODIC_MINOR:
            copy_int_array(melodic_ascending_minor_intervals, si->interval_ascending, 7);
            copy_int_array(melodic_descending_minor_intervals, si->interval_descending, 7);
            break;
    }
    return si;
}
