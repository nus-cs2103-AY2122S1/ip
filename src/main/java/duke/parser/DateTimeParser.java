package duke.parser;

import duke.exceptions.IncorrectFormatException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * The DateTimeParser class encapsulates the formatting of the date and/or time for an event or deadline
 */
public class DateTimeParser extends Parser {

    /**
     * A static method which parses the date of a deadline from String to LocalDate
     * and throws an IncorrectFormatException in the case
     * that the input date is formatted incorrectly
     *
     * @param date input date string
     * @return the parsed date as a LocalDate
     */
    public static LocalDate deadlineDateParse(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
           throw new IncorrectFormatException("Incorrect date format! " +
                   "\nPlease enter a valid date in the given format:" +
                   " yyyy-MM-dd");
        }
    }

    /**
     * Reads the dates from the storage file, parses and returns it.
     * Throws an IncorrectFormatException if the stored date is formatted incorrectly
     *
     * @param date the string value of the date read
     * @return parsed date which is returned as a LocalDate object
     */
    public static LocalDate readDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            throw new IncorrectFormatException("The date has been stored in the wrong format");
        }
    }


    /**
     * A static method which parses the date and Time of an event from String to LocalDateTime
     * and throws an IncorrectFormatException in the case
     * that the input date and/or is formatted incorrectly
     *
     * @param dateTime input dateTime string
     * @return the parsed date as a LocalDate
     */
    public static LocalDateTime eventDateTimeParse(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
            throw new IncorrectFormatException("Incorrect date and time format! " +
                    "\nPlease enter a valid date in the given format:" +
                    " yyyy-MM-dd HHmm");
        }
    }

    /**
     * Reads the dates and times from the storage file, parses and returns it.
     * Throws an IncorrectFormatException if the stored date and/or time are formatted incorrectly
     *
     * @param dateTime the string value of the date read
     * @return parsed date and Time which is returned as a LocalDateTime object
     */
    public static LocalDateTime readDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("MMM dd uuuu HHmm")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
            System.out.println(ex.toString());
            throw new IncorrectFormatException("The date and time have been stored in the wrong format");
        }
    }

}
