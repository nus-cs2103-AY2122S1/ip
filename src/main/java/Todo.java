/**
 * This class encapsulates a Todo task.
 *
 * @author Kleon Ang
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo task.
     *
     * @param description A string describing the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string including the Todo's icon and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}