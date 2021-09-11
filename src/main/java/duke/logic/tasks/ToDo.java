package duke.logic.tasks;

/**
 * Task without any date/time attached to it
 */
public class ToDo extends Task {

    /**
     * Constructor for the Todo task.
     *
     * @param description Description of the task.
     */
    public ToDo(String description, String tag) {
        super(description, tag);
    }

    /**
     * Return string representation of the task to write to hard disk.
     *
     * @return The string representation.
     */
    @Override
    public String toSaveInHardDisk() {
        if (this.isDone) {
            return "T ; 1 ; " + this.description + " ; " + this.tag ;
        } else {
            return "T ; 0 ; " + this.description + " ; " + this.tag;
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
