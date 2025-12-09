#include <stdio.h>

#include "generation_helpers.h"
#include "scale_finder.h"
#include "t_note.h"

int main(void) {

    init_all_notes();

    t_note *NATURAL_NOTES[7] = {
        A_NATURAL,
        B_NATURAL,
        C_NATURAL,
        D_NATURAL,
        E_NATURAL,
        F_NATURAL,
        G_NATURAL
    };



    t_note *SHARP_NOTES[7] = {
        A_SHARP,
        B_SHARP,
        C_SHARP,
        D_SHARP,
        E_SHARP,
        F_SHARP,
        G_SHARP
    };

    t_note *FLAT_NOTES[7] = {
        A_FLAT,
        B_FLAT,
        C_FLAT,
        D_FLAT,
        E_FLAT,
        F_FLAT,
        G_FLAT
    };

    t_note_link_next(B_NATURAL, C_NATURAL);
    t_note_link_next(E_NATURAL, F_NATURAL);
    t_note_link_next(G_SHARP, A_NATURAL);
    t_note_link_next(G_NATURAL, G_SHARP);
    t_note_add_equiv(B_SHARP, C_NATURAL);
    t_note_add_equiv(E_SHARP, F_NATURAL);

    t_note_add_equiv(C_FLAT, B_NATURAL);
    t_note_add_equiv(F_FLAT, E_NATURAL);
    t_note_add_equiv(G_SHARP, A_FLAT);

    for (int i = 0; i < 6; i++) {
        if (!NATURAL_NOTES[i]->next_note) {
            t_note_link_next(NATURAL_NOTES[i], SHARP_NOTES[i]);

        }
        if (SHARP_NOTES[i]->equiv) {
            t_note_link_next(SHARP_NOTES[i], SHARP_NOTES[i + 1]);
        }
        else if (SHARP_NOTES[i] != G_SHARP) {
            t_note_link_next(SHARP_NOTES[i], NATURAL_NOTES[i + 1]);
        }
        else if (!SHARP_NOTES[i]->equiv) {
            t_note_add_equiv(SHARP_NOTES[i], FLAT_NOTES[i + 1]);
        }

    }


    // for (int i = 0; i < 7; i++) {
    //     if (FLAT_NOTES[i]->equiv) {
    //         t_note_link_next(FLAT_NOTES[i], FLAT_NOTES[i + 1]);
    //     }
    //     else if (!FLAT_NOTES[i]->next_note) {
    //         t_note_link_next(FLAT_NOTES[i], SHARP_NOTES[i + 1]);
    //     }
    // }

    t_note *start = A_NATURAL;
    t_note *next = A_NATURAL;
    do {
        printf("name: %s, symbol: %s\n", next->name, next->symbol);
        next = next->next_note;
    } while (next != start);







}
