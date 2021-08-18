/**
 * Represents a task that starts and ends at a specific time.
 * E.g. team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {
    protected String at;

    /**
     * Class constructor
     * @param description Description of the event.
     * @param at Time and Date of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
