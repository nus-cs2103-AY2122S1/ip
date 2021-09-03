package duke.task;

/**
 * Deadline class which Encapsulates task's deadline.
 */
public class Deadline extends Task {
    private DateTime byDate;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadlined task.
     * @param by Date/time of task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = new DateTime(by);
    }

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadline task.
     * @param by Date/time of task deadline.
     * @param isDone Completion status of task.
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        super.isDone = isDone;
        this.byDate = new DateTime(by);
    }

    /**
     * Formats task's data into a string for storage in hard disk.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Deadline // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + byDate.getDate();
    }

    /**
     * Overrides Task class's toString method.
     *
     * @return A String describing details of Deadline class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.toString() + ")\n";
    }
}
