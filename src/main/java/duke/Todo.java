package duke;

/**
 * A task that has no specified date and time.
 *
 * @author Gabriel Goh
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo.
     *
     * @param description Todo description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Convert todo to savable format.
     *
     * @return String to save
     */
    public String saveString() {
        return "T | " + super.saveString();
    }

    /**
     * String representation of todo for printing
     *
     * @return String to print
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
