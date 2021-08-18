/**
 * Represents a task without any date/time attached to it.
 *
 * @author felix-ong
 */
public class Todo extends Task {
    /**
     * Constructor of a Todo Task.
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
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
