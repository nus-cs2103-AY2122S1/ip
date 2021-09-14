package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is the DateTimeUtils class that parse date and time.
 */
public class DateTimeUtils {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * Returns String of date to date format.
     *
     * @param date Date in String format.
     * @return Date format.
     * @throws DateTimeParseException If the date cannot be parsed.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Returns String of time to time format.
     *
     * @param time Time in String format.
     * @return Time format.
     * @throws DateTimeParseException If the time cannot be parsed.
     */
    public static LocalTime parseTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return LocalTime.parse(time, formatter);
    }

    /**
     * Returns String of dateTime to dateTime format.
     *
     * @param dateTime DateTime in String format.
     * @return DateTime format.
     * @throws DateTimeParseException If the dateTime cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDateTime.parse(dateTime, formatter);
    }
}
