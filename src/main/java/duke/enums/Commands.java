package duke.enums;

import java.util.HashMap;
import java.util.Map;

/** Represents the finite commands a user can use. */
public enum Commands {
    LIST("list"),
    ADD("add"),
    DELETE("delete"),
    DONE("done"),
    EXIT("bye"),
    FIND("find"),
    INVALID("");

    /** The register that holds key value pairing between label and enum. */
    private static final Map<String, Commands> BY_LABEL = new HashMap<>();

    /** The label of the enum. */
    private final String label;

    /**
     * Enum constructor.
     *
     * @param label The label of the enum.
     */
    Commands(String label) {
        this.label = label;
    }

    // Registers the Commands into the hashmap.
    static {
        for (Commands command : values()) {
            BY_LABEL.put(command.label, command);
        }
    }

    /**
     * @return A string representation of the Commands enum.
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns the corresponding Command enum based on the label.
     *
     * @param label The label to be enquired.
     * @return The Commands enum.
     */
    public static Commands valueOfLabel(String label) {
        return BY_LABEL.getOrDefault(label, INVALID);
    }
}
