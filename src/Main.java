void main() {
    Note A_flat = new Note("Ab");
    Note A_nat = new Note("a");
    Note B_nat = new Note("b");


    Note a_nat_dbl = new Note("a");
    Note Ab_dbl = new Note("ab");

    System.out.println(A_nat.getComposedSymbol());

    NoteArray na = new NoteArray();
    na.add(A_nat);
    na.add(B_nat);

    System.out.println(na.get("B").getComposedSymbol());

    na.display();

    System.out.println(A_flat.equals("ab"));


    NoteArray sharpBaseNotes = new NoteArray();
    NoteArray flatBaseNotes = new NoteArray();
    String[] sharpBaseNotesStr = new String[12];
    String[] flatBaseNotesStr = new String[12];

    NoteArray.sharpBaseNotes.display();

    Scales majorScale = Scales.MAJOR;

    Scale AMajorScale = new Scale(majorScale, "a");

    AMajorScale.display();
}
