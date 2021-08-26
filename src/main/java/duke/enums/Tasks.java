package duke.enums;

import java.util.HashMap;
import java.util.Map;

public enum Tasks {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    INVALID("");

    private static final Map<String, Tasks> BY_LABEL = new HashMap<>();

    private final String label;

    Tasks(String label) {
        this.label = label;
    }

    static {
        for (Tasks t : values()) {
            BY_LABEL.put(t.label, t);
        }
    }

    @Override
    public String toString() {
        return label;
    }

    public static Tasks valueOfLabel(String label) {
        return BY_LABEL.getOrDefault(label, INVALID);
    }
}
