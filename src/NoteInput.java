import java.util.HashMap;
import java.util.Map;

public class NoteInput {
    private char symbol;
    private char accent;

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

    public NoteInput(Map<String, Character> mappedInput) {
        this.symbol = mappedInput.get(Note.SYMBOL_FLAG);
        this.accent = mappedInput.get(Note.ACCENT_FLAG);
    }

    public static char[] parseString(String s) {
        RequireHandlers.requireValidNoteInput(s);

        return new char[]{s.toUpperCase().charAt(0), s.charAt(1)};
    }

    private static Map<String, Character> mapChar(char symbol, char accent) {
        Map<String, Character> mappedNote = new HashMap<>();
        mappedNote.put(Note.SYMBOL_FLAG, Character.toUpperCase(symbol));
        mappedNote.put(Note.ACCENT_FLAG, accent);

        return mappedNote;
    }

    //region input linting
    public static Map<String, Character> mapString(char symbol, char accent) {
        RequireHandlers.requireValidNoteInput(symbol, accent);
        return mapChar(symbol, accent);
    }

    public static Map<String, Character> mapString(String s) {
        RequireHandlers.requireValidNoteInput(s);
        return mapChar(s.charAt(0), (s.length() == 2) ? s.charAt(1) : Note.NATURAL_SYMBOL);

    }
    public static Map<String, Character> mapString(String symbol, String accent) {
        RequireHandlers.requireValidNoteInput(symbol, accent);
        return mapChar(symbol.charAt(0), accent.charAt(0));
    }
    public static Map<String, Character> mapString(char symbol) {
        RequireHandlers.requireValidNoteInput(symbol);
        return mapChar(symbol, Note.NATURAL_SYMBOL);
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
    public String getComposedSymbol() {
        return "" + this.symbol + this.accent;
    }

    public String toString() {
        return this.getComposedSymbol();
    }

}
