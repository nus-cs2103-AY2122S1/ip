package duke;

/**
 * Represents a task to be done.
 */
public class ToDo extends Task {

    /**
     * Class constuctor that constructs a ToDo object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of ToDo.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
