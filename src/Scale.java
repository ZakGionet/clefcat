public class Scale extends NoteArray<OrderedNote>{
    String name;
    int[] structure;
    private static final NoteFactory<OrderedNote> factory = OrderedNote::new;


    //region Constructors
    public Scale() { super(factory); }

    public Scale(Scales scale) {
        this();
        this.name = scale.getName();
        this.structure = scale.getStructure();
    }

    public Scale(Scales scaleType, String[] paramStrings) {
        this(scaleType);
        for (String s : paramStrings) {
            this.add(new OrderedNote(s));
        }
    }
    public Scale(Scales scaleType, String paramString) {
        this(scaleType, new NoteInput(paramString));
    }

    public Scale(Scales scaleType, char symbol) {
        this(scaleType, new NoteInput(symbol));
    }

    public Scale(Scales scaleType, NoteInput noteInput) {
        this(scaleType);

        boolean rootIsFlat = (noteInput.isFlat() || noteInput.isSymbol('f'));
        NoteArray<Note> baseNoteArray = (rootIsFlat) ? flatBaseNotes : sharpBaseNotes;

        int[] realIndices = generateIndicesFromRootNote(scaleType.getPositions(), baseNoteArray.indexOf(noteInput));

        setNotes(realIndices, baseNoteArray);
    }
    //endregion


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
            OrderedNote orderedNote = new OrderedNote(refNote);

            // Check to see if we're adding a duplicate of a letter, if true: replace its equivalent ScaleNote.
            if (i > 0 && this.get(i - 1).getSymbol() == orderedNote.getSymbol()) {
                orderedNote = new OrderedNote(orderedNote.getEquivalent());
            }

            // Add it to the Scale.
            this.add(orderedNote);

        }
    }

}
