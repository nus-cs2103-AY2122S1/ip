package blue.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import blue.BlueException;

/**
 * Tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    public static final String REPRESENTATION = "E";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final LocalDateTime at;

    /**
     * Constructs an Event.
     *
     * @param title    Title of Event.
     * @param atString The start date and time of an Event, in ISO_LOCAL_DATE_TIME format, e.g. 2021-12-27T23:59:59.
     * @throws BlueException If atString is not in the correct format.
     */
    public Event(String title, String atString) throws BlueException {
        super(title);
        try {
            at = LocalDateTime.parse(atString, FORMATTER);
        } catch (DateTimeParseException e) {
            String message = "Please enter a valid date in the correct format, e.g., 2021-12-27T23:59:59";
            throw new BlueException(message);
        }
    }

    /**
     * Returns the time of the event.
     *
     * @return Time of the event, in String format.
     */
    public String getAt() {
        return at.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Returns the String representation of this instance.
     *
     * @return String representation of this instance.
     */
    @Override
    public String toString() {
        String atRepr = at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return "[" + REPRESENTATION + "]" + super.toString() + " (at: " + atRepr + ")";
    }
}
