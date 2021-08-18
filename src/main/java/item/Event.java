package item;

import exception.BotException;
import exception.EmptyCommandException;

/**
 * Event is a type of Task which happen at a specific timing.
 */
public class Event extends Task {
    protected String at;

    private Event(String description, String at) throws EmptyCommandException {
        super(description);
        this.at = at;
    }

    /**
     * Create an event.
     * @param description The description of Event details.
     * @param at The time when the Event happens.
     * @return The created Event.
     * @throws EmptyCommandException if the description is empty.
     */
    public static Event of(String description, String at) throws EmptyCommandException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("event");
        } else {
            return new Event(description, at);
        }
    }

    /**
     * Return the string representation of Event.
     * @return The string representation of Event, including status, description and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
