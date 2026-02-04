void main() {
//    NoteArray.sharpBaseNotes.display();
//    NoteArray.flatBaseNotes.display();
//
//    Scale AMajorScale = new Scale(majorScale, "a");
//
//    Scale BMajorScale = new Scale(majorScale, 'b');
//
//    AMajorScale.display();
//    BMajorScale.display();

    Scales majorScale = Scales.MAJOR;
    for (char i = 'a'; i <= 'g'; i++) {
        if (i != 'c' && i != 'f') {
            new Scale(majorScale, "" + i + 'b').display();
        }

    }


}
