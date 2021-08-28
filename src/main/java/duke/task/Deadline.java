package duke.task;

/**
 * Deadline class which Encapsulates task's deadline.
 */
public class Deadline extends Task {
    private DateTime by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadlined task.
     * @param by Date/time of task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTime(by);
    }

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadlined task.
     * @param by Date/time of task deadline.
     * @param isDone Completion status of task.
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.by = new DateTime(by);
    }

    /**
     * Formats task's data into a string for storage in hard disk
     * and returns it.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Deadline // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + by.getDate();
    }

    /**
     * Overrides Task class toString method.
     *
     * @return A String describing details of Deadline class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")\n";
    }
}
