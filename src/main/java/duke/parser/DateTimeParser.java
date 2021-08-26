package duke.parser;

import duke.exceptions.IncorrectFormatException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTimeParser extends Parser {

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

    public static LocalDate readDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            throw new IncorrectFormatException("The date has been stored in the wrong format");
        }
    }



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
