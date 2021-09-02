package duke;

/**
 * A class representing a todo task.
 */
public class Todo extends Task {

    /**
     * A constructor for the todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A constructor for the todo task.
     *
     * @param isDone The status of the task.
     * @param description The description of the task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toBackupFormat() {
        return String.format("T | %s | ", super.toBackupFormat());
    }
}
