package duke.task;

/**
 * Represents a ToDo task.
 * Inherits from <code>Task</code>.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo object.
     *
     * @param name Name of the task.
     */
    public ToDo(String name) {
        super(name, "T");
    }

    /**
     * Creates a new ToDo object, allows specifying whether it is complete.
     *
     * @param name Name of the task.
     * @param isComplete Boolean representing whether task is completed.
     */
    public ToDo(String name, boolean isComplete, String[] tags) {
        super(name, isComplete, "T", tags);
    }
}
