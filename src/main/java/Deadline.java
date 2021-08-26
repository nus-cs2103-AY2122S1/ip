/**
 * Deadline (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     * @param deadline Deadline of task.
     */
    public Deadline(String taskstr, String deadline) {
        super(taskstr);
        this.by = deadline;
    }

    @Override
    public String getTime() {
        return this.by;
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.by + ")";
    }

}
