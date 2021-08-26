package duke.enums;

import java.util.HashMap;
import java.util.Map;

public enum Commands {
    LIST("list"),
    ADD("add"),
    DELETE("delete"),
    DONE("done"),
    EXIT("bye"),
    FIND("find"),
    INVALID("");

    private static final Map<String, Commands> BY_LABEL = new HashMap<>();

    private final String label;

    Commands(String label) {
        this.label = label;
    }

    static {
        for (Commands command : values()) {
            BY_LABEL.put(command.label, command);
        }
    }

    @Override
    public String toString() {
        return label;
    }

    public static Commands valueOfLabel(String label) {
        return BY_LABEL.getOrDefault(label, INVALID);
    }
}
