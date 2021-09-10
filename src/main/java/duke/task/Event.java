package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;

/**
 * Event class encapsulates an event task.
 */
public class Event extends Task {
    /**
     * Event Label ie. "E"
     */
    private static final String LABEL = "E";
    /**
     * Date of the event
     */
    protected LocalDate eventDate;

    /**
     * Constructs a Deadline with the specified description and date.
     *
     * @param description Description.
     * @param eventDate   Date of the event.
     * @throws DukeException If either the description or deadline does not follow the format.
     */
    public Event(String description, String eventDate) throws DukeException {
        super(description);
        if (eventDate.isEmpty()) {
            throw new DukeIllegalFormatException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        try {
            this.eventDate = LocalDate.parse(eventDate);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong date format. " + "Try this instead: YYYY-MM-DD"
            );
        }
    }

    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (at: " + eventDate
            .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + ")";
    }

    @Override
    public String toDataString() {
        return LABEL + super.toDataString() + " | " + eventDate;
    }
}


