/**
 * Task without any date/time attached to it
 */
public class ToDo extends Task {

    /**
     * Constructor for the Todo task.
     * 
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of this task.
     * 
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
