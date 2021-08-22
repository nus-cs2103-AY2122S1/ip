/**
 * Encapsulates a todo object.
 * Todo objects are tasks objects without any date/time.
 *
 * @author Dickson
 */
public class ToDo extends Task {
    /**
     * Constructor for Todo object.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets string representation of todo task.
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
