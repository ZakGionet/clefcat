public class ScaleNote extends Note {
    public int degree;
    public Note next;
    public Note previous;
    public int semitonesToNext;

    public ScaleNote(Note note) {
        super(note);
    }
    public ScaleNote(char symbol, char accent) {
        super(NoteArray.getBaseNote(symbol, accent));
    }

    public ScaleNote(String symbol, String accent) {
        super(NoteArray.getBaseNote(symbol, accent));
    }
    public ScaleNote(String str) {
        super(NoteArray.getBaseNote(str));
    }
    public ScaleNote(char symbol) {
        super(symbol);
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
