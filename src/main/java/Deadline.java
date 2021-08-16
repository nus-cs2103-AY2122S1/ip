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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
