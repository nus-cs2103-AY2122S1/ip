package duke.task;

/**
 * Type of Task which is an Event.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDoObject.
     *
     * @param description Description of ToDoObject.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts ToDoObject into String form to save.
     *
     * @return String form of ToDoObject to save.
     */
    @Override
    public String convertToString() {
        return "T|" + super.convertToString();
    }

    /**
     * Converts ToDoObject into its String representation.
     *
     * @return String representation of ToDoObject.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
