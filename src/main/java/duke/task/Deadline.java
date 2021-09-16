package duke.task;

/**
 * Deadline class to represent a task with a deadline.
 */
public class Deadline extends Task {
    private DateTime byDateTime;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadline task.
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
     * Formats task's data into a string for storage in duke.txt.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Deadline // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription()
                + " // " + byDateTime.getDate() + " // " + byDateTime.getTime();
    }

    /**
     * Returns a String describing details of the deadline task.
     *
     * @return A String describing details of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + byDateTime.getFormattedDate() + " "
                + byDateTime.getFormattedTime() + ")\n";
    }
}
