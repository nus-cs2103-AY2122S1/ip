package duke.task;

/**
 * The ToDo class encapsulates a task to be done by the user.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo object that is not completed yet.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a todo object with a specifiable completion status.
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
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the format in which the task is stored in the save file.
     *
     * @return A string representing how the task is saved.
     */
    @Override
    public String getSaveFormat() {
        return "T|" + super.getSaveFormat() + '\n';
    }

    /**
     * Checks whether another object is equal with this task.
     *
     * @param other The object being compared to.
     * @return true if both are tasks and share the same description and completion status, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ToDo) {
            return super.equals(other);
        } else {
            return false;
        }
    }
}
