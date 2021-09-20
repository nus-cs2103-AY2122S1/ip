package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateTimeException;

public class DateTime {
    private static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter YMD_HMS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter HMS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter NATURAL_LANGUAGE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss");

    /**
     * Parses a string into a date.
     *
     * @param dateString The string containing a date.
     */
    public static LocalDate parseDate(String dateString) throws InvalidDateTimeException {
        try {
            return LocalDate.parse(dateString, YMD_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(dateString,
                    "Please use format: YYYY-MM-DD\nEg. 2021-08-08");
        }
    }

    /**
     * Parses a string into a date-time.
     *
     * @param dateString The string containing a date-time.
     */
    public static LocalDateTime parseDateTime(String dateString) throws InvalidDateTimeException {
        try {
            return LocalDateTime.parse(dateString, YMD_HMS_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateString, NATURAL_LANGUAGE_FORMATTER);
            } catch (DateTimeParseException err) {
                throw new InvalidDateTimeException(dateString,
                        "Please use format: YYYY-MM-DD HH:MM:SS or MMM d YYYY HH:MM:SS\n"
                                + "Eg. 2021-08-08 05:05:05 or Aug 8 2021 05:05:05");
            }
        }
    }

    /**
     * Converts a LocalDateTime into a formatted date-time string.
     *
     * @param time The LocalDateTime to format.
     */
    public static String stringifyDateTime(LocalDateTime time) {
        return time.format(NATURAL_LANGUAGE_FORMATTER);
    }

    /**
     * Converts a LocalDateTime into a formatted time string.
     *
     * @param time The LocalDateTime to format.
     */
    public static String stringifyTime(LocalDateTime time) {
        return time.format(HMS_FORMATTER);
    }
}
