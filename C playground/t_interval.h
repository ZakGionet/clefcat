//
// Created by user on 12/12/2025.
//

#ifndef C_PLAYGROUND_T_INTERVAL_H
#define C_PLAYGROUND_T_INTERVAL_H
#include "t_note.h"

typedef enum interval_type interval_type;
typedef struct t_interval t_interval;

#define INTERVALS_LIST   \
X(MINOR_SECOND, "minor second", 1) \
X(MAJOR_SECOND, "major second", 2) \
X(MINOR_THIRD, "minor third", 3)   \
X(MAJOR_THIRD, "major third", 4)   \
X(PERFECT_FOURTH, "perfect fourth", 5) \
X(PERFECT_FIFTH, "perfect fifth", 7)   \
X(MINOR_SIXTH, "minor sixth", 8)   \
X(MAJOR_SIXTH, "major sixth", 9)   \
X(MINOR_SEVENTH, "minor seventh", 10)   \
X(MAJOR_SEVENTH, "major seventh", 11)   \
X(PERFECT_OCTAVE, "perfect octave", 12)


enum interval_type {
#define X(id, str, interval) id,
    INTERVALS_LIST
#undef X
};

struct t_interval {
    char name[MAX_NAME_LENGTH];
    int semitones;
};

char *interval_type_to_string(interval_type interval_type);

interval_type interval_type_get_interval(int nb_semitones);

int interval_type_get_semitones(interval_type interval_type);


#endif //C_PLAYGROUND_T_INTERVAL_H

