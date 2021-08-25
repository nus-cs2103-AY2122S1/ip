/**
 * A Task that needs to be done before a specific date/time.
 *
 * @author Lethicia
 */
public class Deadline extends Task{
    /** date/time by which task must be done. */
    protected String by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description the title or description for the task
     * @param by date/time by which task must be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for a Deadline task.
     *
     * @param description the title or description for the task
     * @param by date/time by which task must be done.
     * @param status boolean indicating true if task is done, false otherwise.
     */
    public Deadline(String description, String by, boolean status) {
        super(description);
        this.by = by;
        this.isDone = status;
    }

    /**
     * Returns the file's details in the format "D,<isDone>,<desc>,<time>"
     * to be stored in the hard disk
     *
     * @return formatted string containing task details
     */
    public String getText() {
        return String.format("D,%s,%s,%s\n", this.isDone, this.description, this.by);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
