/**
 * The ToDo class encapsulates a task to be done by the user.
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
