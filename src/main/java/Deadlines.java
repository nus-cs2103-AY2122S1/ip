/**
 * Class to store deadline. Subclass of task.
 *
 * @author marcuspeh
 * @version Level-7
 * @since 19 Aug 2021
 */
public class Deadlines extends Task {
    private String deadline;

    /**
     * Constructor for Deadline.
     *
     * @param task Task to be stored
     * @param deadline Deadline for the task
     */
    Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline.
     *
     * @param task Task to be stored
     * @param deadline Deadline for the task
     * @param done Whether the task is done
     */
    Deadlines(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    /**
     * To save the task to the txt file.
     * Format is as follow: <Type(D)> | <Description> | <Done> | <Datetime>
     *
     * @return string to save the txt file
     */
    public String saveOutput() {
        return String.format("D | %s | %s | %s", super.getTask(), super.getIsDone() ? 1 : 0, deadline);
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
