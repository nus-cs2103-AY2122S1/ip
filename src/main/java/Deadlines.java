/**
 * Class to store deadline. Subclass of task.
 *
 * @author marcuspeh
 * @version Level-4
 * @since 15 Aug 2021
 */
public class Deadlines extends Task {
    private String deadline;

    /**
     * Constructor for Deadline.
     *
     * @param task task to be stored
     * @param deadline dealine for the task
     */
    Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /** Getter for deadline.
     *
     * @return deadline
     */
    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
