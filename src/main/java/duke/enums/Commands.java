package duke.enums;

import java.util.HashMap;
import java.util.Map;

public enum Commands {
    LIST("list"),
    ADD("add"),
    DELETE("delete"),
    DONE("done"),
    EXIT("bye"),
    INVALID("");

    private final String LABEL;

    private static final Map<String, Commands> BY_LABEL = new HashMap<>();

    Commands(String label) {
        LABEL = label;
    }

    static {
        for (Commands command : values()) {
            BY_LABEL.put(command.LABEL, command);
        }
    }

    @Override
    public String toString() {
        return LABEL;
    }

    public static Commands valueOfLabel(String label) {
        return BY_LABEL.getOrDefault(label, INVALID);
    }
}
