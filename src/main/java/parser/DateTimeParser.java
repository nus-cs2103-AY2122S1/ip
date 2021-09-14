package parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateTimeException;

/**
 * Encapsulates a parser for date time.
 */
public class DateTimeParser {
    /**
     * Changes date from string to `LocalDate`.
     *
     * @param dateString Date string.
     * @param dateFormat Format of date string.
     * @return `LocalDate`.
     * @throws InvalidDateTimeException If there is an error parsing a string to a date.
     */
    public static LocalDate changeDateStringToDate(String dateString, String dateFormat)
            throws InvalidDateTimeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

        try {
            return LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Date", dateFormat);
        }
    }

    /**
     * Changes time from string to `LocalTime`.
     *
     * @param timeString Time string.
     * @param timeFormat Format of time string.
     * @return `LocalTime`.
     * @throws InvalidDateTimeException If there is an error parsing a string to a time.
     */
    public static LocalTime changeTimeStringToTime(String timeString, String timeFormat)
            throws InvalidDateTimeException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);

        try {
            return LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Time", timeFormat);
        }
    }

    /**
     * Changes `LocalDate` to a string in specified format.
     *
     * @param date `LocalDate`.
     * @param dateFormat Format of desired date string.
     * @return String representation of date.
     */
    public static String changeDateToDateString(LocalDate date, String dateFormat) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return dateFormatter.format(date);
    }

    /**
     * Changes `LocalTime` to a string in specified format.
     *
     * @param time `LocalTime`.
     * @param timeFormat Format of desired time string.
     * @return String representation of time.
     */
    public static String changeTimeToTimeString(LocalTime time, String timeFormat) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        return timeFormatter.format(time);
    }
}
