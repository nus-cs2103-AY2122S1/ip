/**
 * The Event class encapsulates an event class.
 */
public class Event extends Task {
    /** The date and time the event will be taking place. */
    protected String at;

    /**
     * Constructor to initialise an Event task.
     * @param description The description of the Event task.
     * @param at The date and time for the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event task.
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
