/**
 * Represents an event, which is a subtype of a Task.
 */
public class Event extends Task {
    protected String description;
    protected String at;

    /**
     * A constructor of an Event.
     *
     * @param description Description of the Event.
     * @param at The date and/or time of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return [E], the description and the at.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
