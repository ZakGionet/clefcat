public class OrderedNote extends Note {
    public int degree;
    public Note next;
    public Note previous;
    public int semitonesToNext;

    public OrderedNote(Note note) {
        super(note);
    }
    public OrderedNote(NoteInput noteInput) {
        super(noteInput.getNoteFromChromatic());
    }
    public OrderedNote(char symbol, char accent) {
        this(new NoteInput(symbol, accent));
    }
    public OrderedNote(String symbol, String accent) {
        this(new NoteInput(symbol, accent));
    }
    public OrderedNote(String str) {
        this(new NoteInput(str));
    }
    public OrderedNote(char symbol) {
        this(new NoteInput(symbol));
    }


    //region setters
    public void setDegree(int degree) {
        this.degree = degree;
    }
    public void setNext(Note note) {
        this.next = note;
    }
    public void setPrevious(Note note) {
        this.previous = note;
    }
    public void setSemitonesToNext(int st) {
        this.semitonesToNext = st;
    }

}
