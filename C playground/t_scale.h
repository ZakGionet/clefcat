//
// Created by user on 12/8/2025.
//

#ifndef C_PLAYGROUND_T_SCALE_H
#define C_PLAYGROUND_T_SCALE_H

#include "t_note.h"

#define MAX_SCALE_COUNT 10

typedef struct t_scale t_scale;
typedef struct t_scale_interval t_scale_interval;
typedef struct t_note t_note;
typedef struct t_twelve_notes t_twelve_notes;

struct t_scale {
    char *name;
    t_note notes_ascending[7];
    t_note notes_descending[7];
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

static int major_intervals[7] = {2,2,1,2,2,2,1};
static int natural_minor_intervals[7] = {2,1,2,2,1,2,2};
static int harmonic_minor_intervals[7] = {2,1,2,2,1,3,1};
static int melodic_ascending_minor_intervals[7] = {2,1,2,2,2,2,1};
static int melodic_descending_minor_intervals[7] = {2,1,2,2,1,2,2};

/* Function t_scale_interval_init
 * Description:
 *      Initializes a struct containing the intervals for a given scale type
 *      Used by t_scale_init
 * Parameters:
 *      - scale_types type: [MAJOR, NATURAL_MINOR, MELODIC_MINOR, HARMONIC_MINOR]
 * Return:
 *      Pointer to a scale interval
 */
t_scale_interval *t_scale_interval_init(scale_types type);

/* Function t_scale_init
 * Description:
 *      Initializes a scale and populates it's ascending_notes
 *      and descending_notes with copies of notes. Copies of notes are linked to each other.
 * Parameters:
 *      - t_note *tonic: A pointer to the root note of the scale
 *      - scale_types type: [MAJOR, NATURAL_MINOR, MELODIC_MINOR, HARMONIC_MINOR]
 * Return:
 *      Pointer to a scale
 */
t_scale *t_scale_init(t_note *tonic, scale_types type);

void t_scale_populate_next_note(t_note *scale_array[7], int next_note_index, t_note *next_note);

/* Function t_scale_populate
 * Description:
 *      Initializes a scale and populates it's ascending_notes
 *      and descending_notes.
 * Parameters:
 *      - t_note *tonic: A pointer to the root note of the scale
 *      - scale_types type: [MAJOR, NATURAL_MINOR, MELODIC_MINOR, HARMONIC_MINOR]
 * Return:
 *      Pointer to a scale
 */
void t_scale_populate(t_scale *scale);

/* Function t_twelve_notes_init
 * Description:
 *      Called by t_scale_init, generates twelve notes, each a half-step up from eachother
 *      starting at the tonic. The accent of the tonic will determine the accents of the
 *      twelve notes. Used to determine the notes of the scale
 * Parameters:
 *      - t_note *tonic: A pointer to the root note of the scale
 * Return:
 *      Pointer to the twelve notes struct
 */
t_twelve_notes *t_twelve_notes_init(t_note *tonic);

/* Function t_twelve_notes_destroy
 * Description:
 *      Destructor for t_twelve_notes
 * Parameters:
 *      - t_twelve_notes *t_twelve_notes: The struct to destroy
 * Return:
 *      None
 */
void t_twelve_notes_destroy(t_twelve_notes *t_twelve_notes);

/* Function t_scale_interval_destroy
 * Description:
 *      Destructor for t_scale_interval
 * Parameters:
 *      - t_scale_interval *t_scale_interval: The struct to destroy
 * Return:
 *      None
 */
void t_scale_interval_destroy(t_scale_interval *t_scale_interval);

t_note *t_scale_get_note_from_interval(t_scale *t_scale);

#endif //C_PLAYGROUND_T_SCALE_H