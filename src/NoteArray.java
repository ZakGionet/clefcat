import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteArray extends ArrayList<Note>{
    private String openingPrint = "[\"";
    private String closingPrint = "\"]";
    private String separator = "\",\"";

    public static NoteArray sharpBaseNotes = new NoteArray(
            new String[]{"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"}
    );
    public static NoteArray flatBaseNotes = new NoteArray(
            new String[]{"A","Bb","B","C","Db","D","Eb","E","F","Gb","G", "Ab"}
    );

    static {
        setBaseArrayEquivalentsStatic();
    }
    public NoteArray() {
        super();
    }

    public NoteArray(String[] strings) {
        this.addAll(strings);
    }

//    public NoteArray(String[] strings, boolean setEquivalent) {
//        this(strings);
//
//
//    }

    public static void setBaseArrayEquivalentsStatic() {
        for (int i = 1; i < 12; i++) {
            Note sharpNote = sharpBaseNotes.get(i);
            Note flatNote = flatBaseNotes.get(i);
            if (!sharpNote.equals(flatNote)) {
                Note.setEquivalent(sharpNote, flatNote);
            }
        }
    }

    public static Note getBaseNote(NoteInput parsedNote) {
        for (Note n : (parsedNote.getAccent() == '-') ? sharpBaseNotes : flatBaseNotes) {
            if (n.equals(parsedNote)) { return n;}
        }
        throw new IllegalArgumentException("cant find it smths wrong: " + parsedNote.toString());
    }

    public static Note getBaseNote(String s) {
        return getBaseNote(new NoteInput(s));

    }

    public static Note getBaseNote(String symbol, String accent) {
        return getBaseNote(new NoteInput(symbol, accent));
    }
    public static Note getBaseNote(char symbol, char accent) {
        return getBaseNote(new NoteInput(symbol, accent));
    }

    public void addAll(Map<String, Character>[] parsedNotes) {
        for (Map<String, Character> pn : parsedNotes) {
            this.add(new Note(pn.get("symbol"), pn.get("accent")));
        }
    }

    public void addAll(String[] strings) {
        for (String s : strings) {
            RequireHandlers.requireValidComposedSymbol(s);

            Map<String, Character> parsedNote = Note.parseString(s);
            this.add(new Note(parsedNote));
        }
    }

    public Note get(NoteInput ni) {
        for (Note n : this) {
            if (n.getAccent() == ni.getAccent() && n.getSymbol() == ni.getSymbol()) {
                return n;
            }
        }
        return null;
    }

    public Note get(String s) {
        return get(new NoteInput(s));

    }

    public Note get(Note note) {
        return (this.contains(note)) ? this.get(this.indexOf(note)) : null;
    }


    public Note getNext(Note note) {
        if (this.size() < 2) {
            throw new ArrayIndexOutOfBoundsException("No next note. Array size = " + this.size());
        }

        Note nextNote;
        if (this.indexOf(note) == this.size() - 1) {
            nextNote = this.get(0);
        }
        else {
            nextNote = this.get(this.indexOf(note));
        }
        return nextNote;
    }

    public Note getPrevious(Note note) {
        if (this.size() < 2) {
            throw new ArrayIndexOutOfBoundsException("No next note. Array size = " + this.size());
        }

        Note previousNote;
        if (this.indexOf(note) == 0) {
            previousNote = this.get(this.size() - 1);
        }
        else {
            previousNote = this.get(this.indexOf(note) - 1);
        }
        return previousNote;
    }

    public Note getDegree(int degree) {
        return this.get(degree + 1);
    }
    public int getDegree(Note note) {
        return this.indexOf(note) + 1;
    }
    public int getDegree(String s) {
        RequireHandlers.requireValidComposedSymbol(s);

        Map<String, Character> parsedNote = Note.parseString(s);

        boolean isComposed = (s.length() == 2);

        for (Note n : this) {
            if (parsedNote.get("symbol") == n.getSymbol() && parsedNote.get("accent") == n.getAccent()) {
                return this.indexOf(n) + 1;
            }
        }
        return -1;
    }

    public void display() {
        System.out.print("Note collection:");
        System.out.print(this.openingPrint);
        for (Note n : this) {
            System.out.print(n.getComposedSymbol());
            if (this.indexOf(n) != this.size() - 1) {
                System.out.print(this.separator);
            }
        }
        System.out.print(closingPrint + '\n');
    }

    public void addAll(Note[] notes) {
        this.addAll(List.of(notes));
    }
}
