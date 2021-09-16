package lifeline.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    /**
     * Parses a given string to a LocalTime object using a specified format.
     *
     * @param time String to be parsed.
     * @return LocalTime representing the time.
     */
    public static LocalTime parseTime(String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime formattedTime = LocalTime.parse(time, timeFormatter);
        return formattedTime;
    }

    /**
     * Parses a given string to a LocalDate object using a specified format.
     *
     * @param date String to be parsed.
     * @return LocalDate representing the date.
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate formattedDate = LocalDate.parse(date, dateFormatter);
        return formattedDate;
    }

}
