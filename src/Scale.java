public class Scale extends NoteArray{
    String name;
    int[] structure;


    public Scale() { super(); }

    public Scale(Scales scale) {
        this.name = scale.getName();
        this.structure = scale.getStructure();
    }

    public Scale(Scales scale, String[] notes) {
        for (String s : notes) {
            this.add(new ScaleNote(s));
        }
    }
    public Scale(Scales scaleType, String s) {
        this.name = scaleType.getName();
        this.structure = scaleType.getStructure();

        Note startNote = NoteArray.getBaseNote(s);

        populateScale(scaleType.getPositions(), startNote);
    }

    private void populateScale(int[] positions, Note rootNote) {
        Note startNote, nextNote;
        NoteArray baseNoteArray = (rootNote.isSharp()) ? NoteArray.sharpBaseNotes : NoteArray.flatBaseNotes;
        startNote = baseNoteArray.get(rootNote);


        this.add(new ScaleNote(startNote));
        for (int i = 1; i < positions.length; i++) {
            for (int j = 0; j < positions[i]; j++) {
                nextNote = baseNoteArray.getNext(startNote);

                this.add(new ScaleNote(nextNote));
                startNote = nextNote;
            }

        }
    }

    private boolean isSharp(NoteInput ni) {
        return (ni.getAccent() == '-' || ni.getAccent() == '#');
    }



    private void populateScale(int[] positions, NoteInput ni) {

        Note rootNote = NoteArray.sharpBaseNotes.get(ni);
    }


}
