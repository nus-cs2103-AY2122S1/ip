package duke.task;

/**
 * Class that extends Task. This task is a deadline and has the additional
 * information of deadline labeled as "by".
 */
public class Deadline extends Task {
    private String by;
    private String tag = "D";

    /**
     * Constructor method for Deadline.
     *
     * @param taskName The main details of the task.
     * @param by The additional information of the task.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Getter method for obtaining the additional information of the task.
     *
     * @return The additional information of the task which is the deadline of the task.
     */
    @Override
    public String getAdditionalInfo() {
        return by;
    }

    /**
     * Getter method to obtain the tag of the task.
     *
     * @return "D" to denote deadline.
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * To String method for tasks.
     *
     * @return The task in String format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
