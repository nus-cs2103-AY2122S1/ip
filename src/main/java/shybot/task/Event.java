package shybot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import shybot.exception.ShyBotException;
import shybot.exception.ShyBotIllegalFormatException;

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
     * @throws ShyBotException If either the description or deadline does not follow the format.
     */
    public Event(String description, String eventDate, String[] tags) throws ShyBotException {
        super(description, tags);
        if (eventDate.isEmpty()) {
            throw new ShyBotIllegalFormatException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        try {
            this.eventDate = LocalDate.parse(eventDate);
        } catch (DateTimeParseException e) {
            throw new ShyBotIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong date format. " + "Try this instead: YYYY-MM-DD"
            );
        }
    }

    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (at: " + eventDate
            .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + ")" + " (tags: " + String.join(", ", this.tags)
            + ")";
    }

    @Override
    public String toDataString() {
        return LABEL + super.toDataString() + " | " + eventDate + " | " + String.join(" ", this.tags);
    }
}


