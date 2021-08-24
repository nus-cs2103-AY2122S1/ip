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
     * Constructor for the ToDo class.
     *
     * @param description The description of the task.
     * @param isDone A boolean indicating whether the task has been completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
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

    @Override
    public String getSaveFormat() {
        return "T|" + super.getSaveFormat() + '\n';
    }
}
