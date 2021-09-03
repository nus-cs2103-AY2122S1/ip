package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;

/**
 * Represents an event with a description and a date that it occurs on.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     * Creates an Event with description and a date.
     *
     * @param description Task description.
     * @param at Date that task occurs on.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
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
     * Returns date that the task represented by this Event occurs on.
     *
     * @return Date that the task represented by this Event occurs on.
     */
    @Override
    public LocalDate getTime() {
        return this.at;
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
            return "[E]" + super.toString() + " (at: " + Parser.parseLocalDate(this.at) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Indicates whether another object is equals to this Event.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equals to this Event.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;

            // Check if done status, description and time are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);
            boolean isTimeSame = this.at.equals(other.at);

            return (isDoneStatusSame && isDescriptionSame && isTimeSame);
        }
        return false;
    }
}
