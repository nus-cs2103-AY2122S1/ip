package tasks;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Creates a Deadline with a specified description and time limit.
     * @param description The description of this Deadline.
     * @param by The time limit of this Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns this task's status and description.
     * @return A string showing what the task is, its description and its time limit.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
