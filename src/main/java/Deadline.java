/**
 * A Deadline-type Task consisting of its details and deadline in day.
 */
public class Deadline extends Task {
    /**
     * The deadline in day.
     */
    protected String by;

    /**
     * Constructs a instance of Deadline that consist of its details and deadline in day.
     *
     * @param taskDetails Description of the task
     * @param by day
     */
    public Deadline(String taskDetails, String by) {
        super(taskDetails);
        this.by = by;
    }

    /**
     * Return the string representation of Deadline details with day and time, prefixed with [D]
     *
     * @return the string representation of Deadline details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation for storing in text file.
     *
     * @return the string representation for storing in text file
     */
    @Override
    public String toStringSave() {
        int completeBinary = 0;
        if (this.isComplete) {
            completeBinary = 1;
        }
        return "D" + " | " + completeBinary + " | " + this.taskDetails + " | " + this.by;
    }

}