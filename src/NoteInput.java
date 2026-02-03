import java.util.Map;

public class NoteInput {
    private char symbol;
    private char accent;

    public NoteInput(String s) {
        RequireHandlers.requireValidComposedSymbol(s);

        Map<String, Character> parsedNote = Note.parseString(s);
        this.symbol = parsedNote.get(Note.symbolFlag);
        this.accent = parsedNote.get(Note.accentFlag);
    }

    public NoteInput(String symbol, String accent) {
        RequireHandlers.requireValidComposedSymbol(symbol, accent);

        this.symbol = symbol.charAt(0);
        this.accent = accent.charAt(0);
    }

    public NoteInput(char symbol, char accent) {
        RequireHandlers.requireValidComposedSymbol(symbol, accent);

        this.symbol = symbol;
        this.accent = accent;
    }

    public char getSymbol() {
        return symbol;
    }
    public char getAccent() {
        return accent;
    }

    public String toString() {
        return "" + this.symbol + this.accent;
    }
}
