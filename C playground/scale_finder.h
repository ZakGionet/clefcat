//
// Created by zak-gionet on 12/5/25.
//



#ifndef C_PLAYGROUND_SCALE_FINDER_H
#define C_PLAYGROUND_SCALE_FINDER_H





typedef struct major_scale major_scale;
typedef struct minor_scale minor_scale;
typedef struct twelve_notes twelve_notes;




int major_intervals[7] = {2,2,1,2,2,2,1};
int natural_minor_intervals[7] = {2,1,2,2,1,2,2};
int harmonic_minor_intervals[7] = {2,1,2,2,1,3,1};
int melodic_ascending_minor_intervals[7] = {2,1,2,2,2,2,1};
int melodic_descending_minor_intervals[7] = {2,1,2,2,1,2,2};









note A_flat = {"A_flat", "Ab", FLAT, NULL};
note A = {"A", "A", NATURAL, NULL};
note A_sharp = {"A_sharp", "A#", SHARP, NULL};

note B_flat = {"B_flat", "Bb", FLAT, NULL};
note B = {"B", "B", NATURAL, NULL};
note B_sharp = {"B_sharp", "B#", SHARP, NULL};

note C_flat = {"C_flat", "Cb", FLAT, NULL};
note C = {"C", "C", NATURAL, NULL};
note C_sharp = {"C_sharp", "C#", SHARP, NULL};

note D_flat = {"D_flat", "Db", FLAT, NULL};
note D = {"D", "D", NATURAL, NULL};
note D_sharp = {"D_sharp", "D#", SHARP, NULL};

note E_flat = {"E_flat", "Eb", FLAT, NULL};
note E = {"E", "E", NATURAL, NULL};
note E_sharp = {"E_sharp", "E#", SHARP, NULL};

note F_flat = {"F_flat", "Fb", FLAT, NULL};
note F = {"F", "F", NATURAL, NULL};
note F_sharp = {"F_sharp", "F#", SHARP, NULL};

note G_flat = {"G_flat", "Gb", FLAT, NULL};
note G = {"G", "G", NATURAL, NULL};
note G_sharp = {"G_sharp", "G#", SHARP, NULL};






major_scale *init_major_scale(char name[MAX_NAME_LENGTH]);
minor_scale *init_minor_scale(char name[MAX_NAME_LENGTH]);
void populate_scale(scale *scale, note *start_note, twelve_notes *twelve_notes);
void init_sharp_twelve_notes(note *start_note);
void init_flat_twelve_notes(note *start_note);





#endif //C_PLAYGROUND_SCALE_FINDER_H