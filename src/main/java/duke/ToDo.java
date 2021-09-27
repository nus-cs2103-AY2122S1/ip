package duke;

/**
 * Represents a type of task that contains just a description.
 */
public class ToDo extends Task {

    /**
     * This constructor creates a ToDo type task object.
     *
     * @param description text description of ToDO task.
     */
    public ToDo(String description) {
        super(description.trim());
    }

    /**
     * This method formats task description and date in a user-friendly way,
     * additionally marking the ToDo task type with [T].
     *
     * @return String of formatted task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
