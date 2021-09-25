package saber.task;

import saber.exceptions.SaberTimeParserException;
import saber.time.SaberTime;

/**
 * Encapsulates an event type task
 */
public class Event extends Task {

    protected SaberTime at;

    /**
     * Constructs for Event type task
     *
     * @param description the description of the event
     * @param at the time of the event
     * @param isDone the completion status of the event
     * @throws SaberTimeParserException if the time is of an unrecognizable format
     */
    public Event(String description, String at, boolean isDone) throws SaberTimeParserException {
        super(description, isDone);
        this.at = new SaberTime(at);
    }

    /**
     * Gets the time of the event
     *
     * @return time of the event in string
     */
    public String getTimeInString() {
        return at.toString();
    }

    /**
     * Returns the string representation of an event task
     *
     * @return String representation of an event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
