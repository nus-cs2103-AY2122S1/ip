/** Represents a Task that should be completed at a specified time period
 *  @author mokdarren
 */
public class Event extends Task {
    protected String at;

    /**
     *
     * @param description description of task
     * @param at time period of Event (start-end)
     */
    public Event(String description, String at)  {
        super(description);
        this.at = at;
    }

    /**
     * Overloaded constructor to specify if completed
     * @param description description of task
     * @param at time period of Event (start-end)
     * @param isDone
     */
    public Event(String description, String at, boolean isDone)  {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
