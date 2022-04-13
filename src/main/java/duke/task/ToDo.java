package duke.task;

/**
 * Normal tasks
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Alternative constructor for initializing from storage.
     *
     * @param isDone Whether the task is already checked.
     * @param description Description of the task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]"
                + super.toString();
    }

    /**
     * Returns a string representation of the todo to be saved as.
     *
     * @return String representation of the todo to be saved as.
     */
    @Override
    public String saveString() {
        return "T|"
                + super.saveString();
    }
}
