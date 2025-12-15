//
// Created by user on 12/12/2025.
//

#include "t_interval.h"

#include <string.h>

#include "t_note.h"




void t_interval_init_all_intervals(t_interval **interval_addr) {
#define X(id, str, interval) \
    *interval_addr =
}

t_interval *t_interval_init(char *name, int semitones) {
    t_interval = malloc
}

char *interval_type_to_string(const interval_type interval_type) {
    switch (interval_type) {
        #define X(id, str, interval) case id: return str;
                INTERVALS_LIST
        #undef X
    }
    return "unknown interval";
}

int interval_type_get_semitones(const interval_type interval_type) {
    switch (interval_type) {
        #define X(id, str, interval) case id: return interval;
                INTERVALS_LIST
        #undef X
    }
    return -1;
}

interval_type interval_type_get_interval(int nb_semitones) {
    switch (nb_semitones) {
        #define X(id, str, interval) case interval: return id;
                INTERVALS_LIST
        #undef X
    }
    return -1;
}