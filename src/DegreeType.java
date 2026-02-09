import java.util.Arrays;

public enum DegreeType {
    ROOT("", "root", 0),
    MINOR_SECOND("minor","second", 1),
    MAJOR_SECOND("major","second", 2),
    MINOR_THIRD("minor","third", 3),
    MAJOR_THIRD("major", "third", 4),
    PERFECT_FOURTH("perfect","fourth", 5),
    AUGMENTED_FOURTH("augmented","fourth", 6),
    DIMINISH_FIFTH("diminished","fifth", 6),
    PERFECT_FIFTH("perfect","fifth", 7),
    MINOR_SIXTH("minor","sixth", 8),
    MAJOR_SIXTH("major","sixth", 9),
    MINOR_SEVENTH("minor","seventh", 10),
    MAJOR_SEVENTH("major","seventh", 11),
    OCTAVE("","octave", 12);

    private final String qualifier;
    private final String type;
    private final int interval;

    private DegreeType(String qualifier, String type, int interval) {
        this.qualifier = qualifier;
        this.type = type;
        this.interval = interval;
    }

    // gotta understand this
    public static DegreeType[] getDegree(int interval) {
        return Arrays.stream(values())
                .filter(d -> d.interval == interval)
                .toArray(DegreeType[]::new);
    }

    public String getName() {
        if (this.qualifier.isEmpty()) {
            return this.type;
        }
        return this.qualifier + " " + this.type;
    }
}
