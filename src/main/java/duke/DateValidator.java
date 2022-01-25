package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateValidator is a class that the bot uses to check if the user inputs
 * a properly formatted date.
 *
 * @author meerian
 */
public class DateValidator {
    /**
     * Represents the format to validate.
     */
    private final DateTimeFormatter format;

    /**
     * Creates a DateValidator with the provided format
     *
     * @param format The format for this object to validate using.
     */
    public DateValidator(DateTimeFormatter format) {
        this.format = format;
    }

    /**
     * Checks if string provided is formatted as a date.
     *
     * @param string The provided input.
     * @return true if string provided is properly formatted, false otherwise.
     */
    public boolean isValid(String string) {
        try {
            LocalDate.parse(string, format);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}
