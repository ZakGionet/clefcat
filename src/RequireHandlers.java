public class RequireHandlers {

    private RequireHandlers() {

    }

    public static void requireCharCount(CharSequence cs) {
        if (cs == null || cs.length() > 1) {
            throw new IllegalArgumentException("Not a valid length: " + cs);
        }
    }

    public static void requireValidSymbol(char symbol) {
        if (Character.toLowerCase(symbol) < 'a' || Character.toLowerCase(symbol) > 'g') {
            throw new IllegalArgumentException("Not a valid symbol: " + symbol);
        }
    }
    public static void requireValidSymbol(CharSequence symbol) {
        requireCharCount(symbol);
        requireValidSymbol(symbol.charAt(0));
    }

    public static void requireValidAccent(char accent) {
        if (
                accent != '#' &&
                        Character.toLowerCase(accent) != 'b' &&
                        accent != '-') {
            throw new IllegalArgumentException("Not a valid accent: " + accent);
        }
    }
    public static void requireValidAccent(CharSequence accent) {
        requireCharCount(accent);
        requireValidAccent(accent.charAt(0));
    }

    //region Constructor Requires
    public static void requireValidComposedSymbol(String paramString) {
        if (paramString == null) {
            throw new IllegalArgumentException("Parameter is null.");
        }
        if (paramString.length() == 1) {
            requireValidComposedSymbol(paramString.charAt(0));
        }
        else {
            requireValidComposedSymbol(paramString.charAt(0), paramString.charAt(1));
        }

    }

    public static void requireValidComposedSymbol(String symbol, String accent) {
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol is null.");
        }
        if (symbol.length() != 1) {
            throw new IllegalArgumentException("Symbol is not 1 char: ");
        }
        if (accent == null) {
            throw new IllegalArgumentException("Accent is null.");
        }
        if (accent.length() != 1) {
            throw new IllegalArgumentException("accent is not 1 char: ");
        }
        requireValidComposedSymbol(symbol.charAt(0), accent.charAt(0));
    }

    public static void requireValidComposedSymbol(char symbol) {
        requireValidSymbol(symbol);
    }

    public static void requireValidComposedSymbol(char symbol, char accent) {
        requireValidSymbol(symbol);
        requireValidAccent(accent);
    }
    //endregion
}
