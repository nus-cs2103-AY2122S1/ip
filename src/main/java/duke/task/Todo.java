package duke.task;

/**
 * This class encapsulates a todo task in the Duke Application.
 */
public class Todo extends Task {
    /**
     * Constructor for a new Todo task.
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a new Todo task.
     *
     * @param description The task description.
     * @param isDone      Whether this task is marked as done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveFormat() {
        return String.format("T,%s", super.toSaveFormat());
    }
}
