/** Represents a Task that should be completed by a specified Date and Time
 *  @author mokdarren
 */
public class Deadline extends Task{
    protected String by;

    /**
     *
     * @param description description of task
     * @param by date and time that the task should be completed by
     */
    public Deadline(String description, String by)  {
        super(description);
        this.by = by;
    }

    /**
     *
     * @param description description of task
     * @param by date and time that the task should be completed by
     * @param isDone
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Getter for string in save-friendly format
     */
    @Override
    public String getSaveFormatString() {
        int isDoneInt = isDone ? 1 : 0;
        return("D," + isDoneInt + "," + description + "," + by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
