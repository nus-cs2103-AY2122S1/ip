package captain.task;

import captain.DukeException.MissingDescriptionException;

/**
 * Represents an event with a date.
 *
 * @author Adam Ho
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates an event with the specified description and date.
     * @param description The task description.
     * @param at The date of event.
     */
    public Event(String description, String at) throws MissingDescriptionException {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    /**
     * Represents event object as a string.
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
