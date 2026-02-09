import java.util.ArrayList;
import java.util.Map;

public class NoteArray<S extends Note> extends ArrayList<S>{
    private String openingPrint = "[\"";
    private String closingPrint = "\"]";
    private String separator = "\",\"";
    public static final int BASE_ARRAY_LENGTH = 12;
    private final NoteFactory<S> factory;

    public static final NoteArray<Note> sharpBaseNotes = new NoteArray<>(Note::new);
    public static final NoteArray<Note> flatBaseNotes = new NoteArray<>(Note::new);
    public static final NoteArray<Note> equivBaseNotes = new NoteArray<>(Note::new);

    static {
        sharpBaseNotes.addAllBase(new String[]{"A","A#","B","C","C#","D","D#","E","F","F#","G","G#"});
        flatBaseNotes.addAllBase(new String[]{"A","Bb","B","C","Db","D","Eb","E","F","Gb","G","Ab"});
        setBaseArrayEquivalentsStatic();
        Note.setEquivalent(sharpBaseNotes.get("B"), new Note("Cb"));
        Note.setEquivalent(flatBaseNotes.get("B"), new Note("Cb"));
        Note.setEquivalent(sharpBaseNotes.get("C"), new Note("B#"));
        Note.setEquivalent(flatBaseNotes.get("C"), new Note("B#"));

        Note.setEquivalent(sharpBaseNotes.get("E"), new Note("Fb"));
        Note.setEquivalent(flatBaseNotes.get("E"), new Note("Fb"));
        Note.setEquivalent(sharpBaseNotes.get("F"), new Note("E#"));
        Note.setEquivalent(flatBaseNotes.get("F"), new Note("E#"));
    }


    public NoteArray(NoteFactory<S> factory) {
        this.factory = factory;
    }
    @SuppressWarnings("unchecked") // Class localized constructor for base chromatic note arrys
    private NoteArray() {
        this.factory = mappedString -> (S) new Note(mappedString);
    }


    public static void setBaseArrayEquivalentsStatic() {
        for (int i = 1; i < 12; i++) {
            Note sharpNote = sharpBaseNotes.get(i);
            Note flatNote = flatBaseNotes.get(i);
            if (sharpNote.isNatural() && sharpNote.getSymbol() == flatNote.getSymbol()) {
                Note.setEquivalent(sharpNote, flatNote);
            }
            else if (!sharpNote.equals(flatNote)) {
                Note.setEquivalent(sharpNote, flatNote);
            }
        }
    }

    public void addAll(String[] strings) {
        for (String s : strings) {
            this.add(factory.create(new NoteInput(s)));
        }
    }

    private void addAllBase(Map<String, Character>[] mappedNotes) {
        for (Map<String, Character> mappedNote : mappedNotes) {
            this.add(factory.create(new NoteInput(mappedNote)));
        }
    }

    private void addAllBase(NoteInput[] noteInputs) {
        for (NoteInput ni : noteInputs) {
            this.add(factory.create(ni));
        }
    }

    private void addAllBase(String[] strings) {
        for (String s : strings) {
            this.add(factory.create(new NoteInput(s)));
        }
    }
    private void addAllBase(Note[] notes) {
        for (Note note : notes) {
            this.add(factory.create(new NoteInput(note)));
        }
    }



    public int indexOf(NoteInput ni) {
        for (int i = 0; i < this.size(); i++)
        {
            if (this.get(i).equals(ni)) {
                return i;
            }
        }
        return -1;
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
        RequireHandlers.requireValidNoteInput(s);

        Map<String, Character> parsedNote = NoteInput.mapString(s);

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


}
