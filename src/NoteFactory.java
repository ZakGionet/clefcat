@FunctionalInterface
public interface NoteFactory<S extends Note> {
    S create(NoteInput NoteInput);
}
