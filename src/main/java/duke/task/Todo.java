package duke.task;

/**
 * Represents the Todo task in the Duke program.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this Todo task to be saved in storage.
     *
     * @return the string representation of this Todo task to be saved in storage.
     */
    @Override
    public String toStringData() {
        return String.format("T | %s", super.toStringData());
    }

    /**
     * Returns a description of this Todo task.
     *
     * @return a description of this Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
