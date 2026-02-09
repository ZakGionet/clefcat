import java.util.ArrayList;

public class Chord<S extends Note> extends NoteArray<OrderedNote> {
    private String name;
    private ArrayList<DegreeType[]> degrees = new ArrayList<>();
    private Scales scale;
    private static final NoteFactory<OrderedNote> factory = OrderedNote::new;
    public static final int MAX_NOTES = 12;

    public Chord() {
        super(factory);
    }
    public Chord(S s) {
        super(factory);
        this.add(factory.create(s.getNoteInput()));
    }

    private void setDegree(NoteInput noteInput) {
        if (!this.isEmpty()) {
            int semitonesBetween = NoteInput.semitonesBetween(this.get(0).getNoteInput(), noteInput);
            this.degrees.add(DegreeType.getDegree(semitonesBetween));
        }
        else {
            this.degrees.add(new DegreeType[]{DegreeType.ROOT});
        }
    }

    private void _add(NoteInput noteInput) {
        this.add(factory.create(noteInput));
        setDegree(noteInput);
    }

    public void add(NoteInput noteInput) {
        this._add(noteInput);
    }
    public void add(S s) {
        this._add(s.getNoteInput());
    }
    public void add(String paramString) {
        this._add(new NoteInput(paramString));
    }
    public void add(char symbol) {
        this._add(new NoteInput(symbol));
    }

    public ArrayList<DegreeType[]> getDegrees() {
        return this.degrees;
    }

    public void display() {
        for (int i = 0; i < this.size(); i++) {
            System.out.print(this.get(i).getComposedSymbol() + " (" + this.getDegrees().get(i)[0].getName() + ")\n");
        }
    }
}
