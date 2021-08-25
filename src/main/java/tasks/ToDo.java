package tasks;

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
     * Return string representation of the task to write to hard disk.
     *
     * @return The string representation.
     */
    @Override
    public String toSaveInHardDisk() {
        if (this.isDone) {
            return "T ; 1 ; " + this.description;
        } else {
            return "T ; 0 ; " + this.description;
        }
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
