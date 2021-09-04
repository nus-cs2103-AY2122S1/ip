package duke.task;

import java.time.LocalDate;

import duke.Parser;
import duke.exception.DukeException;

/**
 * Represents an event with a description and a date that it occurs on.
 */
public class Event extends TimedTask {
    /**
     * Constructor for Event.
     * Creates an Event with description and a date.
     *
     * @param description Task description.
     * @param at Date that task occurs on.
     */
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    /**
     * Returns string identifier for an Event.
     * String identifier for an Event is "E".
     *
     * @return String identifier for an Event.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a formatted string that starts with this Event's string identifier, followed by
     * its done status and description, and finally its date of occurrence.
     *
     * @return Formatted description of this Event.
     */
    @Override
    public String toString() {
        try {
            return "[" + this.getTaskType() + "]"
                    + super.toString() + " (at: " + Parser.parseLocalDate(super.date) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }
}
