package duke.enums;

import java.util.HashMap;
import java.util.Map;

/** Represents the finite task types a user can create. */
public enum Tasks {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    INVALID("");

    /** The register that holds key value pairing between label and enum. */
    private static final Map<String, Tasks> BY_LABEL = new HashMap<>();

    /** The label of the enum. */
    private final String label;

    /**
     * Enum constructor.
     *
     * @param label The label of the enum.
     */
    Tasks(String label) {
        this.label = label;
    }

    // Registers the Tasks into the hashmap.
    static {
        for (Tasks t : values()) {
            BY_LABEL.put(t.label, t);
        }
    }

    /**
     * @return A string representation of the Tasks enum.
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns the corresponding Tasks enum based on the label.
     *
     * @param label The label to be enquired.
     * @return The Tasks enum.
     */
    public static Tasks valueOfLabel(String label) {
        return BY_LABEL.getOrDefault(label, INVALID);
    }
}
