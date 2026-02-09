import java.util.Map;

public class RequireHandlers {

    private RequireHandlers() {

    }

    public static void requireCharCount(CharSequence cs, int min, int max) {
        requireNotNull(cs);

        if (cs.length() < min) {
            throw new IllegalArgumentException("Invalid string length: " + cs.length() + ". (too short).");
        }
        if (cs.length() > max) {
            throw new IllegalArgumentException("Invalid string length: " + cs.length() + ". (too long).");
        }
    }

    public static void requireCharCount(CharSequence cs, int exactLength) {
        requireNotNull(cs);

        if (cs.length() != exactLength) {
            throw new IllegalArgumentException(
                    "Invalid string length: " + cs.length() + ". String must contain " + exactLength + " characters."
            );
        }
    }

    public static void requireNotNull(CharSequence cs) {
        if (cs == null) {
            throw new IllegalArgumentException("Invalid String: null string");
        }
    }
    public static void requireNotNull(CharSequence[] paramStrings) {
        for (CharSequence cs: paramStrings) {
            requireNotNull(cs);
        }
    }

    public static void requireValidSymbol(char symbol) {
        if (Character.toLowerCase(symbol) < 'a' || Character.toLowerCase(symbol) > 'g') {
            throw new IllegalArgumentException("Not a valid symbol: " + symbol);
        }
    }

    public static void requireValidSymbol(CharSequence symbol) {
        requireCharCount(symbol, 1, 1);
        requireValidSymbol(symbol.charAt(0));
    }

    public static void requireValidAccent(char accent) {
        if (
                accent != Note.SHARP_SYMBOL
                &&
                accent != Note.NATURAL_SYMBOL
                &&
                Character.toLowerCase(accent) != Note.FLAT_SYMBOL) {
            throw new IllegalArgumentException("Not a valid accent: " + accent);
        }
    }

    public static void requireValidAccent(CharSequence accent) {
        requireCharCount(accent, 1);
        requireValidAccent(accent.charAt(0));
    }

    public static void requireValidOctave(char octave) {
        int intValue = Character.getNumericValue(octave);
        if (intValue < 0 || intValue > 9) {
            throw new IllegalArgumentException("Invalid octave.");
        }
    }

    public static void requireValidOctave(int octave) {
        if (octave < 0 || octave > 9) {
            throw new IllegalArgumentException("Invalid octave.");
        }
    }

    //region Constructor Requires
    public static void requireValidNoteInput(String paramString) {
        requireCharCount(paramString, 1, 3);
        switch (paramString.length()) {
            case 1:
                requireValidNoteInput(paramString.charAt(0));
                break;
            case 2:
                requireValidNoteInput(paramString.charAt(0), paramString.charAt(1));
                break;
            case 3:
                requireValidNoteInput(paramString.charAt(0), paramString.charAt(1), paramString.charAt(2));
                break;
        }
    }

    public static void requireValidNoteInput(char symbol, char accent, char octave) {
        requireValidSymbol(symbol);
        requireValidAccent(accent);
        requireValidOctave(octave);
    }

    // this is added because a user could construct a Map and pass it to the public
    // NoteInput constructor.
    public static void requireValidNoteInput(Map<String, Character> mappedString) {
        requireValidSymbol(mappedString.get(Note.SYMBOL_FLAG));
        requireValidAccent(mappedString.get(Note.ACCENT_FLAG));
    }

    public static void requireValidNoteInput(String symbol, String accent) {
        requireValidNoteInput(symbol);
        requireValidNoteInput(accent);
    }

    public static void requireValidNoteInput(char symbol) {
        requireValidSymbol(symbol);
    }

    public static void requireValidNoteInput(char symbol, char accent) {
        requireValidSymbol(symbol);
        requireValidAccent(accent);
    }
    //endregion
}
