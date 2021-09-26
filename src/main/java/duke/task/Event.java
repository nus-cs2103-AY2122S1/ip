package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The Event class encapsulates a task that occurs at a specific date.
 */
public class Event extends Task {
    /** The date that the event occurs at. */
    private LocalDate at;

    /**
     * Constructs an event object that is not completed yet.
     *
     * @param description The description of the event.
     * @param at The date that the event occurs at.
     * @throws DukeException If the deadline of the task is not in YYYY-MM-DD format.
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
     * Constructs an event object with a specifiable completion status.
     *
     * @param description The description of the event.
     * @param isDone A boolean indicating whether the task has been completed.
     * @param at The date that the event occurs at.
     * @throws DukeException If the deadline of the task is not in YYYY-MM-DD format.
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Event dates should be in the form YYYY-MM-DD.");
        }
    }

    /**
     * Returns the string representation of the event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ")";
    }

    /**
     * Returns the format in which the event is stored in the save file.
     *
     * @return A string representing how the event is saved.
     */
    @Override
    public String getSaveFormat() {
        return "E|" + super.getSaveFormat() + "|" + this.at + '\n';
    }

    /**
     * Checks whether another object is equal with this event.
     *
     * @param other The object being compared to.
     * @return true if both are events and share the same date, false otherwise.
     */
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
