package task;

/**
 * Represents a task without any date/time attached to it.
 *
 * @author felix-ong
 */
public class Todo extends Task {
    /**
     * Constructor of a Todo Task.
     *
     * @param description Short description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of a Todo Task.
     *
     * @param description Short description of task.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task to be saved in.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();
    }

    /**
     * Returns the string representation of a Todo Task.
     *
     * @return The string representation of a Todo Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
