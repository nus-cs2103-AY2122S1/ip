/**
 * The Event class encapsulates a task that occurs at a specific date/time.
 */
public class Event extends Task {
    /** The date/time that the event occurs at. */
    private String at;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @param at The date/time that the event occurs at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return A string representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
