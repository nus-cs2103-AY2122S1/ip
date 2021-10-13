package duke;

/**
 * This class encapsulates a Todo task.
 *
 * @author Kleon Ang
 */
public class Todo extends Task {
    private static final String taskBadge = "[T]";

    /**
     * Constructor for a Todo task.
     *
     * @param description A string describing the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a data string of the Todo task for saving.
     *
     * @return A data string including the Todo's icon and description.
     */
    @Override
    public String getDataString() {
        return "T | " + super.getDataString();
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string including the Todo's icon and description.
     */
    @Override
    public String toString() {
        return taskBadge + super.toString();
    }
}
