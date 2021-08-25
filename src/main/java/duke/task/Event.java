package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class encapsulates an event task.
 */
public class Event extends Task {
    /**
     * Date of the event
     */
    protected LocalDate at;
    /**
     * Event Label ie. "E"
     */
    private static final String LABEL = "E";

    /**
     * Constructs a Deadline with the specified description and date.
     *
     * @param description Description.
     * @param at          Date of the event.
     * @throws DukeException If either the description or deadline does not follow the format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.isEmpty()) {
            throw new DukeIllegalFormatException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalFormatException(
                    "☹ OOPS!!! Seems like you have entered a wrong date format. " +
                            "Try this instead: YYYY-MM-DD"
            );
        }
    }

    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return LABEL + super.toDataString() + " | " + at;
    }
}


