//
// Created by user on 12/8/2025.
//
#include "t_note.h"

#ifndef C_PLAYGROUND_T_SCALE_H
#define C_PLAYGROUND_T_SCALE_H

typedef struct t_scale t_scale;
typedef struct t_scale_interval t_scale_interval;

struct t_scale {
    char *name;
    note *notes_ascending;
    note *notes_descending;
    scale_types type;
};

struct t_scale_interval {
    scale_types scale_type;
    int interval_ascending[7];
    int interval_descending[7];
};

struct twelve_notes {
    note notes[12];
};

t_scale_interval *t_scale_interval_init(scale_types type);
t_scale *t_scale_init(note *tonic, scale_types type);
void t_scale_populate(t_scale *scale);
t_twelve_notes *t_twelve_notes_init(t_note *tonic);
#endif //C_PLAYGROUND_T_SCALE_H