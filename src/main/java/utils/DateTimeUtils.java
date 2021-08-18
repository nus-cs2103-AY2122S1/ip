package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The is the DateTimeUtils class that parse date and time.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class DateTimeUtils {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * Parse String of date to date format.
     *
     * @param  date String
     * @return date format
     * @throws DateTimeParseException if the date cannot be parsed
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Parse String of time to time format.
     *
     * @param  time String
     * @return time format
     * @throws DateTimeParseException if the time cannot be parsed
     */
    public static LocalTime parseTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        return LocalTime.parse(time, formatter);
    }

    /**
     * Parse String of dateTime to dateTime format.
     *
     * @param  dateTime String
     * @return dateTime format
     * @throws DateTimeParseException if the dateTime cannot be parsed
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDateTime.parse(dateTime, formatter);
    }
}
