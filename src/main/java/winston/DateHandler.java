package winston;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that handles parsing and formatting of dates
 */
public class DateHandler {
    public DateHandler() {
    }

    /**
     * A method that will convert a date in strong format to LocalDate format
     * @param str String of the date
     * @return date of type Local Date
     */
    public static LocalDate readDate(String str) {
        return LocalDate.parse(str);
    }

    /**
     * A method that will convert date to a different format and return a string
     * 
     * @param date date of Type LocalDate to be converted to a string in a different format
     * @return date of type string in format d MMM yyyy
     */
    public static String convertDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
