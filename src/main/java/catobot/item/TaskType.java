package catobot.item;

/**
 * Represents different types of tasks.
 */
public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    TASK("task"),
    INVALID("");

    private final String name;

    TaskType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Finds the matching TaskType of a name.
     *
     * @param name The string of name of task.
     * @return The corresponding TaskType to the name.
     */
    public static TaskType find(String name) {
        for (TaskType t : TaskType.values()) {
            if (name.equals(t.name)) {
                return t;
            }
        }
        return INVALID;
    }
}
