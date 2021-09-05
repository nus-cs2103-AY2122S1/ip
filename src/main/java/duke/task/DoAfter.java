package duke.task;

/**
 * A DoAfter task that is a task that is supposed to be done after another.
 */
public class DoAfter extends Task {
    private String after;
    private String tag = "DA";
    /**
     * Class constructor for the Task class.
     *
     * @param taskDetails The main input details for the task.
     */
    public DoAfter(String taskDetails, String after) {
        super(taskDetails);
        this.after = after;
    }

    /**
     * Getter method to obtain the tag of the task.
     *
     * @return "DA" to denote Do After.
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * Getter method for obtaining the additional information of the task.
     *
     * @return The additional information of the task which is after.
     */
    @Override
    public String getAdditionalInfo() {
        return after;
    }

    /**
     * To String method for tasks.
     *
     * @return The task in String format.
     */
    @Override
    public String toString() {
        return "[DA]" + super.toString() + " (after: " + after + ")";
    }
}
