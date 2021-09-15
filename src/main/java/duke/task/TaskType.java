package duke.task;

/**
 * A TaskType represents a type of Task in the application.
 */
public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    /**
     * The name of the TaskType.
     */
    private final String name;

    /**
     * Creates a TaskType with the specified name.
     * @param name Name of the TaskType.
     */
    TaskType(String name) {
        this.name = name;
    }

    /**
     * Gets the String representation of the TaskType.
     * @return The String representation of the TaskType
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Parses the input String into an equivalent TaskType.
     * @return The equivalent TaskType.
     */
    public static TaskType parse(String string) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.name.equals(string)) {
                return taskType;
            }
        }

        throw new RuntimeException(String.format("Invalid duke.task.TaskType string %s.", string));
    }
}
