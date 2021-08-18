import java.util.HashMap;
import java.util.Map;

public enum Tasks {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    INVALID("");

    private final String LABEL;

    private static final Map<String, Tasks> BY_LABEL = new HashMap<>();

    Tasks(String label) {
        LABEL = label;
    }

    static {
        for (Tasks t : values()) {
            BY_LABEL.put(t.LABEL, t);
        }
    }

    @Override
    public String toString() {
        return LABEL;
    }

    public static Tasks valueOfLabel(String label) {
        return BY_LABEL.getOrDefault(label, INVALID);
    }
}
