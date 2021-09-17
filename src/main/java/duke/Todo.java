package duke;

/**
 * Represents the Todo task.
 */
public class Todo extends Task {
    /**
     * Class Constructor for the Todo task.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a Todo task.
     *
     * @return String representation of a Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of a Todo task for storage purpose.
     *
     * @return String representation of a Todo task for storage purpose.
     */
    @Override
    public String toHistory() {
        return "T" + super.toHistory();
    }
}
