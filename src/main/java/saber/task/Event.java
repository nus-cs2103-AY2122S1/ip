package saber.task;

import saber.SaberTime;
import saber.exceptions.SaberTimeParserException;

/**
 * A class to encapsulate an event type task
 */
public class Event extends Task {

    protected SaberTime at;

    /**
     * A constructor for Event type task
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
     * A function to get the time of the event
     * @return time of the event in string
     */
    public String getTimeInString() {
        return at.toString();
    }

    /**
     * A function to return the string representation of an event task
     * @return String representation of an event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}