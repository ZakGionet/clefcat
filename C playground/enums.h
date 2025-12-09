//
// Created by user on 12/8/2025.
//

#ifndef C_PLAYGROUND_ENUMS_H
#define C_PLAYGROUND_ENUMS_H

// 1. Define the list once
#define SCALE_TYPE_LIST                     \
    X(MAJOR, "major")                       \
    X(NATURAL_MINOR, "minor natural")       \
    X(HARMONIC_MINOR, "minor harmonic")     \
    X(MELODIC_MINOR, "minor melodic")

// 2. Generate the enum
enum scale_types;

// 3. Generate the string conversion function
char* scale_type_to_string(enum scale_types s);


// 1. Define the list
#define ACCENTS_LIST    \
X(FLAT, "Flat")         \
X(NATURAL, "Natural")   \
X(SHARP, "Sharp")       \

// 2. Generate the enum
enum accents;

// 3. String conversion function
char* accents_to_string(enum accents a);




#endif //C_PLAYGROUND_ENUMS_H