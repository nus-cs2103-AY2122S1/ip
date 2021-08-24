package duke.task;

/**
 * A duke.task.TaskType represents a type of duke.task.Task in the application.
 */
public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    /**
     * The name of the duke.task.Task.
     */
    private final String name;

    /**
     * Creates a duke.task.TaskType with the specified name.
     * @param name Name of the duke.task.TaskType.
     */
    TaskType(String name) {
        this.name = name;
    }

    /**
     * Gets the String representation of the duke.task.TaskType.
     * @return The String representation of the duke.task.TaskType
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Parse the input String into an equivalent duke.task.TaskType.
     * @return The equivalent duke.task.TaskType.
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
