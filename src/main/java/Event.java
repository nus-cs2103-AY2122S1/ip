/**
 * A Task that start at a specific date/time and
 * ends at a specific date/time.
 *
 * @author Lethicia
 */
public class Event extends Task{
    /** date/time of the event. */
    protected String at;

    /**
     * Constructor for an Event task.
     *
     * @param description the title or description for the task
     * @param at date/time of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
