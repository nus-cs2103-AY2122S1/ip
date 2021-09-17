package duke;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter NATURAL_LANGUAGE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss");

    /**
     * Parses a string into a date.
     *
     * @param dateString The string containing a date.
     */
    public static LocalDateTime parse(String dateString) throws DukeException {
        try {
            return LocalDateTime.parse(dateString, YMD_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateString, NATURAL_LANGUAGE_FORMATTER);
            } catch (DateTimeParseException err) {
                throw new InvalidDateTimeException(dateString);
            }
        }
    }

    public static String stringify(LocalDateTime time) {
        return time.format(NATURAL_LANGUAGE_FORMATTER);
    }
}
