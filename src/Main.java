void main() {
//    NoteArray.sharpBaseNotes.display();
//    NoteArray.flatBaseNotes.display();
//
//    Scales majorScale = Scales.MAJOR;
//    Scale AMajorScale = new Scale(majorScale, "a");
//
//    Scale BMajorScale = new Scale(majorScale, 'b');
//
//    AMajorScale.display();
//    BMajorScale.display();
//
//    for (char i = 'a'; i <= 'g'; i++) {
//        if (i != 'c' && i != 'f') {
//            new Scale(majorScale, "" + i + 'b').display();
//        }
//
//    }
//
//    char accent[] = new char[]{'b', '-', '#'};
//
//    for (char i = 'A'; i <= 'G'; i++) {
//        for (int n = 0; n < 3; n++) {
//            for (char j = 'A'; j <= 'G'; j++) {
//                for (int m = 0; m < 3; m++) {
//                    System.out.println(String.valueOf(i) + accent[n] + " <> " + j + accent[m] + ": " + NoteInput.semitonesBetween("" + i + accent[n] + '0', "" + j + accent[m] + '1'));
//                }
//            }
//        }
//    }

    Chord<OrderedNote> A_maj_chord = new Chord<>();

    A_maj_chord.add("A-0");
    A_maj_chord.add("C-0");
    A_maj_chord.add("E-0");
    A_maj_chord.add("Fb0");
    A_maj_chord.add("B#0");
    A_maj_chord.add("G#0");
    A_maj_chord.add("Gb0");
    A_maj_chord.display();

}
