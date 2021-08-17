/**
 * This class encapsulates an Event task.
 *
 * @author Kleon Ang
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for an Event task.
     *
     * @param description A string describing the Event task.
     * @param at A string indicating when the event is held at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string including the Event's icon, description and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}