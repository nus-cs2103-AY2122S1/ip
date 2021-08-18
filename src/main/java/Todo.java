/**
 * The To-do class encapsulates a To-do task.
 */
public class Todo extends Task {
    /**
     * Constructor to initialise a To-do task.
     * @param description The description of a To-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the To-do task.
     * @return String representation of the To-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
