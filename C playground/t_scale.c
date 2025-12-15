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
    s->name = malloc(sizeof(char) * MAX_NAME_LENGTH);
    if (!s->name) {
        return NULL;
    }

    // concat e.g 'A' with ' '
    strcpy(s->name, tonic->symbol);
    strcat(s->name, " ");

    // Concat e.g 'A ' with 'major'
    strcat(s->name, scale_type_to_string(type));

    // SET type
    s->type = type;

    // SET ascending - descending
    t_twelve_notes *t_tn = t_twelve_notes_init(tonic);
    t_scale_interval *t_si = t_scale_interval_init(type);

    // Set first element of arrays to copies of tonic
    s->notes_ascending[0] = *t_note_create_copy(tonic, NULL, NULL);
    s->notes_descending[0] = *t_note_create_copy(tonic, NULL, NULL);

    // Cumulative interval for lookup in twelve note scale.
    int ascending_interval = 0;
    int descending_interval = 0;
    for (int i = 1; i < 7; i++) {

        // Increment the ascending interval by the value stored in the scale interval->interval array
        ascending_interval += t_si->interval_ascending[i - 1];

        // Populate the ascending scale at index i
        t_scale_populate_next_note(&s->notes_ascending, i, &t_tn->notes[ascending_interval]);

        // Increment the descending interval by the value stored in the scale interval->interval array
        descending_interval += t_si->interval_descending[i - 1];

        // Populate the descending scale at index i
        t_scale_populate_next_note(&s->notes_descending, i, &t_tn->notes[ascending_interval]);
    }

    // Destroy twelve notes and scale interval
    t_twelve_notes_destroy(t_tn);
    t_scale_interval_destroy(t_si);
    return s;
}

void t_scale_populate_next_note(t_note **scale_array, int next_note_index, t_note *next_note) {
    // Check the previous and current note symbols ignoring accents e.g. 'A', 'B'
    // If they are the same, create a copy of the equivalence in the ascending scale array
    char prev_note_symbol = scale_array[next_note_index - 1]->symbol[0];
    char next_note_symbol = next_note->symbol[0];

    if (prev_note_symbol + 1 != next_note_symbol) {
        scale_array[next_note_index] = t_note_create_copy(next_note->equiv, NULL, NULL);
    }
    // If not, create a copy of the note in the ascending scale array
    else {
        scale_array[next_note_index] = t_note_create_copy(next_note, NULL, NULL);
    }

    //Link the previous note in the scale to the newly added note
    t_note_link_next(scale_array[next_note_index -1], scale_array[next_note_index]);
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
        // If the tonic's accent doesn't match the next notes accent, switch to its equivalent.
        if (tonic->accent != NATURAL && tonic->accent != next_note->accent) {
            next_note = next_note->equiv;
        }
        // Set the next note in the array
        t_tn->notes[i] = *next_note;

        // Assign the following note in the set
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
