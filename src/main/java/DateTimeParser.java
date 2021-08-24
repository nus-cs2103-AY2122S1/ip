import exceptions.IncorrectFormatException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTimeParser {

    public static LocalDate deadlineDateParse(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
           throw new IncorrectFormatException("Incorrect date format! " +
                   "\nPlease enter a valid date in the given format:" +
                   " yyyy-MM-dd");
        }
    }

    public static LocalDateTime eventDateTimeParse(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException ex) {
            throw new IncorrectFormatException("Incorrect date and time format! " +
                    "\nPlease enter a valid date in the given format:" +
                    " yyyy-MM-dd HHmm");
        }
    }

}
