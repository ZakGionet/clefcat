import java.util.HashMap;
import java.util.Map;

public class NoteInput {
    private final char symbol;
    private final char accent;
    private final int octave;

    public NoteInput(String s) {
        Map<String, Character> mappedInput = mapString(s);
        this(mappedInput);
    }

    public NoteInput(char symbol) {
        Map<String, Character> mappedInput = mapString(symbol);
        this(mappedInput);
    }
    public NoteInput(String symbol, String accent) {
        Map<String, Character> mappedInput = mapString(symbol, accent);
        this(mappedInput);
    }

    public NoteInput(char symbol, char accent) {
        Map<String, Character> mappedInput = mapString(symbol, accent);
        this(mappedInput);
    }

    public NoteInput(Note note) {
        Map<String, Character> mappedInput = mapString(
                note.getSymbol(), note.getAccent(), (char) (note.getOctave() + '0')
        );
        this(mappedInput);
    }

    public NoteInput(Map<String, Character> mappedInput) {
        RequireHandlers.requireValidNoteInput(mappedInput);

        this.symbol = mappedInput.get(Note.SYMBOL_FLAG);
        this.accent = mappedInput.get(Note.ACCENT_FLAG);
        this.octave = Character.getNumericValue(mappedInput.get((Note.OCTAVE_FLAG)));
    }

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
        int index = this.getIndexFromChromatic();

        if (index != -1) {
            return this.getChromaticArrayRef().get(index);
        }
        if (this.isFlat()) {
            return NoteArray.flatBaseNotes.get(index);
        }
        return NoteArray.sharpBaseNotes.get(index);
    }
    public static Note getNoteFromChromatic(NoteInput noteInput) {
        return noteInput.getChromaticArrayRef().get(noteInput);
    }

    public static char[] parseString(String s) {
        RequireHandlers.requireValidNoteInput(s);

        return new char[]{s.toUpperCase().charAt(0), s.charAt(1)};
    }

    private static Map<String, Character> mapChar(char symbol, char accent) {
        Map<String, Character> mappedNote = new HashMap<>();
        mappedNote.put(Note.SYMBOL_FLAG, Character.toUpperCase(symbol));
        mappedNote.put(Note.ACCENT_FLAG, accent);
        mappedNote.put(Note.OCTAVE_FLAG, Note.DEFAULT_OCTAVE);

        return mappedNote;
    }

    private static Map<String, Character> mapChar(char symbol, char accent, char octave) {
        Map<String, Character> mappedNote = mapChar(symbol, accent);
        mappedNote.put(Note.OCTAVE_FLAG, octave);

        return mappedNote;
    }

    //region input linting
    public static Map<String, Character> mapString(char symbol, char accent) {
        RequireHandlers.requireValidNoteInput(symbol, accent);
        return mapChar(symbol, accent);
    }

    public static Map<String, Character> mapString(char symbol, char accent, char octave) {
        RequireHandlers.requireValidNoteInput(symbol, accent, octave);
        return mapChar(symbol, accent, octave);
    }

    public static Map<String, Character> mapString(String s) {
        RequireHandlers.requireValidNoteInput(s);
        return switch (s.length()) {
            case 1 -> mapChar(s.charAt(0), Note.NATURAL_SYMBOL, Note.DEFAULT_OCTAVE);
            case 2 -> mapChar(s.charAt(0), s.charAt(1), Note.DEFAULT_OCTAVE);
            case 3 -> mapChar(s.charAt(0), s.charAt(1), s.charAt(2));
            default -> null;
        };
    }

    public static Map<String, Character> mapString(String symbol, String accent) {
        RequireHandlers.requireValidNoteInput(symbol, accent);
        return mapChar(symbol.charAt(0), accent.charAt(0), Note.DEFAULT_OCTAVE);
    }
    public static Map<String, Character> mapString(char symbol) {
        RequireHandlers.requireValidNoteInput(symbol);
        return mapChar(symbol, Note.NATURAL_SYMBOL, Note.DEFAULT_OCTAVE);
    }

    private int indexOfAnnoyingNotes() {
        return switch (this.getComposedSymbol()) {
            case "Cb" -> NoteArray.sharpBaseNotes.indexOf(new NoteInput("B"));
            case "B#" -> NoteArray.sharpBaseNotes.indexOf(new NoteInput("C"));
            case "E#" -> NoteArray.sharpBaseNotes.indexOf(new NoteInput("F"));
            case "Fb" -> NoteArray.sharpBaseNotes.indexOf(new NoteInput("E"));
            default -> -1;
        };
    }

    private int getIndexFromChromatic() {
        int index = this.indexOfAnnoyingNotes();

        if (index == -1) {
            return this.getChromaticArrayRef().indexOf(this);
        }
        return index;

    }

    public static int semitonesBetween(NoteInput rootNote, NoteInput nextNote) {

        int rootIndex = rootNote.getIndexFromChromatic();
        int nextIndex = nextNote.getIndexFromChromatic();

        int stBetween = NoteArray.BASE_ARRAY_LENGTH * (nextNote.getOctave() - rootNote.getOctave());

        if (nextIndex < rootIndex) {
            stBetween += NoteArray.BASE_ARRAY_LENGTH - (rootIndex - nextIndex);
        }
        else {
            stBetween += nextIndex - rootIndex;
        }
        return stBetween;
    }
    public static int semitonesBetween(String rootParamString, String nextParamString) {
        return semitonesBetween(new NoteInput(rootParamString), new NoteInput(nextParamString));
    }


    public boolean equals(NoteInput noteInput) {
        return  (this.getSymbol() == noteInput.getSymbol() && this.getAccent() == noteInput.getAccent());
    }
    public boolean equals(String paramString) {
        return equals(new NoteInput(paramString));
    }

    public boolean isSharp() {
        return (this.getAccent() == Note.SHARP_SYMBOL);
    }

    public boolean isFlat() {
        return (this.getAccent() == Note.FLAT_SYMBOL);
    }
    public boolean isNatural() {
        return (this.getAccent() == Note.NATURAL_SYMBOL);
    }
    public boolean isSymbol(NoteInput ni) {
        return (this.symbol == ni.symbol);
    }
    public boolean isSymbol(char symbol) {
        return isSymbol(new NoteInput(Character.toUpperCase(symbol)));
    }
    public boolean isSymbol(CharSequence paramString) {
        RequireHandlers.requireCharCount(paramString, 1);
        return isSymbol(paramString.charAt(0));
    }


    public char getSymbol() {
        return symbol;
    }
    public char getAccent() {
        return accent;
    }
    public int getOctave() {
        return octave;
    }

    public String getComposedSymbol() {
        return "" + this.symbol + this.accent;
    }

    public String toString() {
        return this.getComposedSymbol();
    }

}
