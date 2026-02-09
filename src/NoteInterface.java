public interface NoteInterface {
    public char getSymbol();
    public char getAccent();
    public NoteInput getNoteInput();
    public boolean isSharp();
    public boolean isFlat();
    public boolean isNatural();
    public boolean equals(NoteInput noteInput);
    public String toString();
    public NoteArray<Note> getChromaticArrayRef();
}
