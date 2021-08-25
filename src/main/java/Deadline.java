/**
 * Task that need to be done before a specific date/time
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for deadline
     * 
     * @param description The description of the task.
     * @param by The deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Return string representation of the task to write to hard disk.
     * 
     * @return The string representation.
     */
    @Override
    public String toSaveInHardDisk() {
        if (this.isDone) {
            return "D - 1 - " + this.description + " - " + this.by;
        } else {
            return "D - 0 - " + this.description + " - " + this.by;
        }
    }
    
    /**
     * String representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}