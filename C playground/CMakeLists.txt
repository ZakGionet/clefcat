cmake_minimum_required(VERSION 4.0)
project(C_playground C)

set(CMAKE_C_STANDARD 11)

add_executable(C_playground main.c
        scale_finder.c
        scale_finder.h
        t_note.c
        t_note.h
        t_scale.c
        t_scale.h
        enums.c
        enums.h
        string_manip.c
        string_manip.h
        generation_helpers.c
        generation_helpers.h
        notes_list.c
        notes_list.h
        t_interval.c
        t_interval.h)
