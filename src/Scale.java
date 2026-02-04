public class Scale extends NoteArray<ScaleNote>{
    String name;
    int[] structure;


    //region Constructors
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
    public Scale(Scales scaleType, String paramString) {
        this(scaleType, new NoteInput(paramString));
    }

    public Scale(Scales scaleType, char symbol) {
        this(scaleType, new NoteInput(symbol));
    }

    public Scale(Scales scaleType, NoteInput ni) {
        this.name = scaleType.getName();
        this.structure = scaleType.getStructure();

        boolean rootIsFlat = (ni.isFlat() || ni.isSymbol('f'));
        NoteArray<Note> baseNoteArray = (rootIsFlat) ? flatBaseNotes : sharpBaseNotes;

        int[] realIndices = generateIndicesFromRootNote(scaleType.getPositions(), baseNoteArray.indexOf(ni));

        setNotes(realIndices, baseNoteArray);
    }
    //endregion

    public void setNext() {
        for (ScaleNote scaleNote: this) {

        }
    }

    int[] generateIndicesFromRootNote(int[] positions, int startIndex) {
        int baseScaleLength = NoteArray.BASE_ARRAY_LENGTH;

        if (startIndex >= baseScaleLength || startIndex < 0) {
            throw new IllegalArgumentException("Start index out of bounds for baseNoteArray.");
        }

        int[] realIndices = new int[7];
        int potentialIndex;

        for (int i = 0; i < positions.length; i++) {
            potentialIndex = positions[i] + startIndex;
            realIndices[i] = (potentialIndex >= baseScaleLength) ? potentialIndex - baseScaleLength : potentialIndex;
        }
        return realIndices;
    }

    private void setNotes(int[] baseArrayIndices, NoteArray<Note> baseNoteArray) {
        for (int i = 0; i < baseArrayIndices.length; i++) {
            // reference to a note in the baseNoteArray
            Note refNote = baseNoteArray.get(baseArrayIndices[i]);

            // Create new ScaleNote from Note reference
            ScaleNote scaleNote = new ScaleNote(refNote);

            // Check to see if we're adding a duplicate of a letter, if true: replace its equivalent ScaleNote.
            if (i > 0 && this.get(i - 1).getSymbol() == scaleNote.getSymbol()) {
                scaleNote = new ScaleNote(scaleNote.getEquivalent());
            }

            // Add it to the Scale.
            this.add(scaleNote);

        }
    }

}
