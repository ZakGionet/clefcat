//
// Created by user on 12/8/2025.
//

#include "generation_helpers.h"

#include <string.h>


t_note *init_all_notes_static() {

    char notes[7] = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    static t_note note_array[21];

    for (int i = 0; i < 7; i++) {
        char note_symbol[3];
        note_symbol[0] = notes[i];

        note_symbol[1] = 'b';
        note_symbol[2] = '\0';

        char note_name[MAX_NAME_LENGTH];

        note_name[0] = notes[i];
        note_name[1] = '\0';
        strcat(note_name, " flat");

        note_array[i * 3] = *t_note_init(note_name, note_symbol, FLAT);

        note_name[1] = '\0';
        note_symbol[1] = ' ';
        strcat(note_name, " natural");
        note_array[i * 3 + 1] = *t_note_init(note_name, note_symbol, NATURAL);

        note_name[1] = '\0';
        note_symbol[1] = '#';
        strcat(note_name, " sharp");
        note_array[i * 3 + 2] = *t_note_init(note_name, note_symbol, SHARP);

    }
    return note_array;
}
