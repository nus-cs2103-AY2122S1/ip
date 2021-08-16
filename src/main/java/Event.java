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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
