package duke;

/**
 * A class to represent a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo object.
     * @param description name of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the task information
     *
     * @return the task information
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
