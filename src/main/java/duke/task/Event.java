package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class encapsulates a task that occurs at a specific date/time.
 */
public class Event extends Task {
    /** The date/time that the event occurs at. */
    private LocalDate at;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @param at The date/time that the event occurs at.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Event dates should be in the form YYYY-MM-DD.");
        }
    }

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the event.
     * @param isDone A boolean indicating whether the task has been completed.
     * @param at The date/time that the event occurs at.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Event dates should be in the form YYYY-MM-DD.");
        }
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return A string representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E|" + super.getSaveFormat() + "|" + this.at + '\n';
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return super.equals(otherEvent) && this.at.equals(otherEvent.at);
        } else {
            return false;
        }
    }
}
