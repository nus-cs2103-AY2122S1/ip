package duke.task;

/**
 * Deadline class which Encapsulates task's deadline.
 */
public class Deadline extends Task {
    private DateTime byDateTime;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadlined task.
     * @param byDate Date of task deadline.
     * @param byTime Time of task deadline.
     */
    public Deadline(String description, String byDate, String byTime) {
        super(description);
        this.byDateTime = new DateTime(byDate, byTime);
    }

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadline task.
     * @param byDate Date of task deadline.
     * @param byTime Time of task deadline.
     * @param isDone Completion status of task.
     */
    public Deadline(String description, String byDate, String byTime, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.byDateTime = new DateTime(byDate, byTime);
    }

    /**
     * Formats task's data into a string for storage in hard disk.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Deadline // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + byDateTime.getDate() + " // " + byDateTime.getTime();
    }

    /**
     * Overrides Task class's toString method.
     *
     * @return A String describing details of Deadline class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + byDateTime.getFormattedDate() + " "
                + byDateTime.getFormattedTime() + ")\n";
    }
}
