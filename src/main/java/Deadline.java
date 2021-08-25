/**
 * Represents tasks with deadline.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */

public class Deadline extends Task {

    protected String by;
    private final String TASK_MARKER = "D";

    /**
     * Class constructor for Deadline class.
     * 
     * @param description the task description.
     * @param by the time by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Print out the deadline task,
     * 
     * @return string format of the deadline task, 
     * consisting of the task marker "[D]", task description and deadline of the task.
     */
    @Override
    public String toString() {
        return String.format("| %s | %s | %s", TASK_MARKER, super.toString(), by);
    }

    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }
}
