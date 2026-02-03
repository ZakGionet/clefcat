import java.util.Iterator;

public enum Scales {
    MAJOR("major", new int[]{2, 2, 1, 2, 2, 2, 1}),
    MINOR_NATURAL("minor natural", new int[]{2,1,2,2,1,2,2}),
    MINOR_HARMONIC("minor harmonic", new int[]{2,1,2,2,1,3,1}),
    MINOR_MELODIC_ASCENDING("minor melodic ascending", new int[]{2,1,2,2,2,2,1});


    private final String name;
    private final int[] structure;
    private int[] positions = new int[7];

    private Scales(String name, int[] structure) {
        this.name = name;
        this.structure = structure;

        calculatePositions(structure);
    }

    private void calculatePositions(int[] structure) {
        this.positions[0] = 0;

        int currentSum = 0;
        // kinda janky, just skipping over adding the last one
        for (int i = 0; i < structure.length - 1; i++) {
            currentSum += structure[i];
            this.positions[i + 1] = currentSum;
        }
    }

    public String getName() {
        return this.name;
    }

    public int[] getStructure() {
        return this.structure;
    }

    public int[] getPositions() {
        return this.positions;
    }

    public Scales getScale(String name) {
        switch (name) {
            case "major":
                return MAJOR;
            case "minor natural":
                return MINOR_NATURAL;
            case "minor harmonic":
                return MINOR_HARMONIC;
            case "minor melodic":
                return MINOR_MELODIC_ASCENDING;
            default:
                throw new IllegalArgumentException("Not a valid scale.");
        }
    }
}


