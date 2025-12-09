//
// Created by user on 12/8/2025.
//

#ifndef C_PLAYGROUND_T_SCALE_H
#define C_PLAYGROUND_T_SCALE_H

#include "t_note.h"


typedef struct t_scale t_scale;
typedef struct t_scale_interval t_scale_interval;
typedef struct t_note t_note;
typedef struct t_twelve_notes t_twelve_notes;

struct t_scale {
    char *name;
    t_note *notes_ascending;
    t_note *notes_descending;
    scale_types type;
};

struct t_scale_interval {
    scale_types scale_type;
    int interval_ascending[7];
    int interval_descending[7];
};

struct t_twelve_notes {
    t_note notes[12];
};

int major_intervals[7] = {2,2,1,2,2,2,1};
int natural_minor_intervals[7] = {2,1,2,2,1,2,2};
int harmonic_minor_intervals[7] = {2,1,2,2,1,3,1};
int melodic_ascending_minor_intervals[7] = {2,1,2,2,2,2,1};
int melodic_descending_minor_intervals[7] = {2,1,2,2,1,2,2};

t_scale_interval *t_scale_interval_init(scale_types type);
t_scale *t_scale_init(t_note *tonic, scale_types type);
void t_scale_populate(t_scale *scale);
t_twelve_notes *t_twelve_notes_init(t_note *tonic);
void t_twelve_notes_destroy(t_twelve_notes *t_twelve_notes);
void t_scale_interval_destroy(t_scale_interval *t_scale_interval);
#endif //C_PLAYGROUND_T_SCALE_H