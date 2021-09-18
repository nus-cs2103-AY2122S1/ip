package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    public static final String IDENTIFIER = "T";

    /**
     * Creates a todo task.
     *
     * @param description Task's description.
     * @param isDone Status of task's completion.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a todo task.
     *
     * @param description Task's description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString();
    }

    @Override
    public String toStorageFormat() {
        return IDENTIFIER + Task.STORAGE_DELIMITER + super.toStorageFormat();
    }
}
