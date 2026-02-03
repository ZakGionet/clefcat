import java.util.HashMap;
import java.util.Map;



public class Note {
    private String composedSymbol;
    private char symbol;
    private char accent;
    private Note equivalent = null;
    private Note sharp = null;
    private Note flat = null;
    public static final String symbolFlag = "symbol";
    public static final String accentFlag = "accent";




    //region input linting
    public static Map<String, Character> parseString(String s) {
        Map<String, Character> parsedNote= new HashMap<>();

        if (s.length() < 1 || s.length() > 2) {
            throw new IllegalArgumentException("Not a valid size string");
        }
        parsedNote.put(symbolFlag, s.toUpperCase().charAt(0));
        parsedNote.put(accentFlag, (s.length() == 2) ? s.charAt(1) : '-');

        return parsedNote;
    }

    //region Constructor Helpers
    private static char extractSymbol(String s) {
        if (s == null || s.length() < 1 || s.length() > 2) {
            throw new IllegalArgumentException("Must be 1 character.");
        }
        return s.charAt(0);
    }

    private static char extractAccent(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Passed null.");
        }
        if (s.length() == 1) {
            return '-';
        }
        return s.charAt(1);
    }
    //endregion

    //region Constructors
    // Here, we learnt that you CANNOT call another constructor conditionally, or after
    // other code. ALL logic must be delegated to the constructor called. Here, we were
    // trying to have verification logic in an overloaded constructor

    public Note(Note note) {
        this.composedSymbol = note.composedSymbol;
        this.symbol = note.symbol;
        this.accent = note.accent;
        this.equivalent = note.equivalent;
        this.sharp = note.sharp;
        this.flat = note.flat;
    }

    public Note(Map<String, Character> parsedNote) {
        this(parsedNote.get(symbolFlag), parsedNote.get(accentFlag));
    }

    public Note(char symbol, char accent) {
        RequireHandlers.requireValidComposedSymbol(symbol, accent);

        this.symbol = Character.toUpperCase(symbol);
        this.accent = accent;
        this.composedSymbol = "" + this.symbol + (accent == '-' ? "" : accent);
    }
    public Note(char symbol) {
        this(symbol, '-');
    }
    public Note(String s) {
        this(
                extractSymbol(s),
                extractAccent(s)
        );
    }
    public Note(CharSequence symbol, CharSequence accent) {
        this(
                symbol.charAt(0),
                accent.charAt(0)
        );
    }
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

    public String getComposedSymbol() {
        return this.composedSymbol;
    }

    public Note getEquivalent() {
        return this.equivalent;
    }
    //endregion


    public boolean isSharp() {
        return (this.getAccent() == '-' || this.getAccent() == '#');
    }

    public boolean isFlat() {
        return (this.accent == 'b');
    }

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
        RequireHandlers.requireValidComposedSymbol(symbol);
        return (this.symbol == Character.toUpperCase(symbol));
    }

    public String toString() {
        return this.composedSymbol;
    }
}
