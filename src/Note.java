import java.util.Map;



public class Note implements NoteInterface{
    private final String composedSymbol;
    private char symbol;
    private char accent;
    private int octave;
    private Note equivalent = null;
    private Note sharp = null;
    private Note flat = null;
    public static final String SYMBOL_FLAG = "symbol";
    public static final String ACCENT_FLAG = "accent";
    public static final String OCTAVE_FLAG = "octave";
    public static final char SHARP_SYMBOL = '#';
    public static final char FLAT_SYMBOL = 'b';
    public static final char NATURAL_SYMBOL = '-';
    public static final char DEFAULT_OCTAVE = '0';

    //region Constructors
    // Here, we learnt that you CANNOT call another constructor conditionally, or after
    // other code. ALL logic must be delegated to the constructor called. Here, we were
    // trying to have verification logic in an overloaded constructor

    public Note(Note note) {
        this.composedSymbol = note.composedSymbol;
        this.symbol = note.symbol;
        this.accent = note.accent;
        this.octave = note.octave;
        this.equivalent = note.equivalent;
        this.sharp = note.sharp;
        this.flat = note.flat;
    }

    public Note(NoteInput noteInput) {
        this.symbol = noteInput.getSymbol();
        this.accent = noteInput.getAccent();
        this.octave = noteInput.getOctave();
        this.composedSymbol = noteInput.getComposedSymbol();
    }

    public Note(char symbol) { this(new NoteInput(symbol)); }
    public Note(char symbol, char accent) { this(new NoteInput(symbol, accent)); }
    public Note(String paramString) { this(new NoteInput(paramString)); }
    public Note(String symbol, String accent) { this( new NoteInput(symbol, accent) ); }
    public Note(Map<String, Character> parsedNote) { this(new NoteInput(parsedNote)); }
    //endregion


    //region Setters
    public void setSymbol(String s) {
        this.setSymbol(s.charAt(0));
    }
    public void setSymbol(char c) {
        RequireHandlers.requireValidSymbol(c);
        this.symbol = Character.toUpperCase(c);
    }

    public void setAccent(String s) {
        this.setAccent(s.charAt(0));
    }
    public void setAccent(char c) {
        RequireHandlers.requireValidAccent(c);
        this.accent = c;
    }

    public void setOctave(int octave) {
        RequireHandlers.requireValidOctave(octave);
        this.octave = octave;
    }


    public void setEquivalent(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Passed null.");
        }

        this.equivalent = note;
    }

    public static void setEquivalent(Note note1, Note note2) {
        if (note1 == null || note2 == null) {
            throw new IllegalArgumentException("Passed null.");
        }

        note1.equivalent = note2;
        note2.equivalent = note1;
    }

    public void setSharp(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Passed null.");
        }
        this.sharp = note;
    }

    public void setFlat(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Passed null.");
        }
        this.flat = note;
    }
    //endregion

    //region Getters
    public char getSymbol() {
        return this.symbol;
    }
    public char getAccent() {
        return this.accent;
    }
    public int getOctave() { return this.octave; }

    public String getComposedSymbol() {
        return this.composedSymbol;
    }

    public Note getEquivalent() {
        return this.equivalent;
    }

    public NoteInput getNoteInput() {return new NoteInput(this);}
    //endregion


    /** Provides a reference to the chromatic array where the "equivalent" note resides.
     *  By default, if a note is natural or sharp it'll return the sharp chromatic scale.
     * @return NoteArray.sharpBaseNotes or NoteArray.flatBaseNotes
     */
    public NoteArray<Note> getChromaticArrayRef() {
        return (this.isSharp() || this.isNatural()) ? NoteArray.sharpBaseNotes : NoteArray.flatBaseNotes;
    }

    /** Returns a reference to the note in the chromatic scale it belongs to.
    *  By default, if a note is sharp or natural, it will belong to the sharp chromatic scale.
    */
    public Note getNoteFromChromatic() {
        return this.getChromaticArrayRef().get(this.getNoteInput());
    }

    public static <S extends Note> int semitonesBetween(S rootNote, S nextNote) {
        return NoteInput.semitonesBetween(rootNote.getNoteInput(), nextNote.getNoteInput());
    }

    public boolean isSharp() {
        return (this.getAccent() == NATURAL_SYMBOL || this.getAccent() == SHARP_SYMBOL);
    }

    public boolean isFlat() {
        return (this.accent == FLAT_SYMBOL);
    }
    public boolean isNatural() { return (this.accent == NATURAL_SYMBOL); }

    public boolean equals(Note n) {
        return (n.composedSymbol.equals(this.composedSymbol));
    }
    public boolean equals(String composedSymbol) {
        return equals(new NoteInput(composedSymbol));
    }

    public boolean equals(NoteInput parsedNote) {
        return (parsedNote.getAccent() == this.getAccent() && parsedNote.getSymbol() == this.getSymbol());
    }

    public boolean equals(char symbol) {
        RequireHandlers.requireValidNoteInput(symbol);
        return (this.symbol == Character.toUpperCase(symbol));
    }

    public String toString() {
        return this.composedSymbol;
    }

}
