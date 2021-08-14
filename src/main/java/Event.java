/**
 * This Event class implements the characteristics of a task
 * that starts at a specific time and ends at a specific time.
 *
 * @author Yeo Jun Wei
 */
public class Event extends Task {

    /** The date/time on which the event is held */
    protected String at;

    /**
     * Constructor for an Event instance that takes in a description and time frame.
     *
     * @param description The given task description.
     * @param at The time frame in which the event is held.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of an Event instance.
     *
     * @return A string representing an Event instance.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
