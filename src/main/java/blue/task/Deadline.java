package blue.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import blue.BlueException;

/**
 * Tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final LocalDateTime by;

    /**
     * Constructs a Deadline.
     *
     * @param title    Title of Deadline.
     * @param byString Due date and time of Deadline, in ISO_LOCAL_DATE_TIME format, e.g. 2021-12-27T23:59:59.
     * @throws BlueException If byString is not in the correct format.
     */
    public Deadline(String title, String byString) throws BlueException {
        super(title);
        try {
            by = LocalDateTime.parse(byString, FORMATTER);
        } catch (DateTimeParseException e) {
            String message = "Please enter a valid date in the correct format, e.g., 2021-12-27T23:59:59";
            throw new BlueException(message);
        }
    }

    /**
     * Returns a String that represents this class.
     *
     * @return String that represents this class.
     */
    public static String getClassRepr() {
        return "D";
    }

    /**
     * Returns the deadline.
     *
     * @return Deadline in String format.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Returns the String representation of this instance.
     *
     * @return String representation of this instance.
     */
    @Override
    public String toString() {
        String byRepr = by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return "[D]" + super.toString() + " (by: " + byRepr + ")";
    }
}
