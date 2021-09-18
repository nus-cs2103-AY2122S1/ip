package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConversion {

    /**
     * Converts from string format to date format.
     *
     * @param text string format of the date.
     * @return date.
     */
    public static LocalDateTime textToDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(text, formatter);
        return date;
    }

    /**
     * Converts from date format to string format.
     *
     * @param date date.
     * @return string text version of the date.
     */
    public static String dateToText(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
    }
}
