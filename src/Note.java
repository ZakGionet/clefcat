public class Note {
    String composedSymbol;
    char symbol;
    char accent;
    Note equiv;


    //region Constructor Requires
    private static void requireValidConstructorParams(CharSequence paramString) {
        if (paramString == null) {
            throw new IllegalArgumentException("Parameter is null.");
        }
        if ( paramString.length() > 2) {
            throw new IllegalArgumentException("Parameter is longer than 2 chars: " + paramString);
        }
        if (paramString.length() == 1) {
            requireValidConstructorParams(paramString.charAt(0));
        }
        requireValidConstructorParams(paramString.charAt(0), paramString.charAt(1));
    }

    private static void requireValidConstructorParams(CharSequence symbol, CharSequence accent) {
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
        requireValidConstructorParams(symbol.charAt(0), accent.charAt(0));
    }

    private static void requireValidConstructorParams(char symbol) {
        if (Character.toLowerCase(symbol) < 'a' || Character.toLowerCase(symbol) > 'g') {
            throw new IllegalArgumentException("Symbol is not within a-g: " + symbol);
        }
    }

    private static void requireValidConstructorParams(char symbol, char accent) {
        if (
                accent != '#' &&
                Character.toLowerCase(accent) != 'b' &&
                accent != '-') {
            throw new IllegalArgumentException("Not a valid accent: " + accent);
        }
        requireValidConstructorParams(symbol);
    }
    //endregion

    //region Constructor Helpers
    private static char extractSymbol(CharSequence cs) {
        if (cs == null || cs.length() < 1 || cs.length() > 2) {
            throw new IllegalArgumentException("Must be 1 character.");
        }
        return cs.charAt(0);
    }

    private static char extractAccent(CharSequence cs) {
        if (cs == null) {
            throw new IllegalArgumentException("Passed null.");
        }
        if (cs.length() == 1) {
            return '-';
        }
        return cs.charAt(1);
    }
    //endregion

    //region Constructors
    // Here, we learnt that you CANNOT call another constructor conditionally, or after
    // other code. ALL logic must be delegated to the constructor called. Here, we were
    // trying to have verification logic in an overloaded constructor
    public Note(char symbol, char accent) {
        requireValidConstructorParams(symbol, accent);

        this.symbol = symbol;
        this.accent = accent;
        this.composedSymbol = "" + symbol + accent;
    }
    public Note(char symbol) {
        this(symbol, '-');
    }
    public Note(CharSequence composedSymbol) {
        this(
                extractSymbol(composedSymbol),
                extractAccent(composedSymbol)
        );
    }
    public Note(CharSequence symbol, CharSequence accent) {
        this(
                symbol.charAt(0),
                accent.charAt(0)
        );
    }
    //endregion

}
