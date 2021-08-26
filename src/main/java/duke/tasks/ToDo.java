package duke.tasks;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do task.
     *
     * @param description The task description.
     * @param isDone The status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a new to-do task.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
