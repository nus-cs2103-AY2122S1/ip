package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Utility {
    private final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Parses given input string of format 'dd/MM/yyyy HHmm' and converts it
     * to a LocalDateTimeObject
     *
     * @param date input string that represents date time in 'dd/MM/yyyy HHmm' format
     * @return new LocalDateTime object that corresponds to input string
     * @throws DateTimeParseException if string does not follow format
     */
    public static LocalDateTime parseDate(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, inputFormatter);
    }

    /**
     * Converts a LocalDateTime object to its string representation in 
     * 'MMM dd yyyy. HH:mm' format
     *
     * @param date LocalDateTime object to convert to its ouput string representation
     * @return new String that corresponds to input LocalDateTime object
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(outputFormatter);
    }

    /**
     * Converts a LocalDateTime object to its file string representation in 
     * 'dd/MM/yyyy HHmm' format
     *
     * @param date LocalDateTime object to convert to its file string representation
     * @return new String that corresponds to input LocalDateTime object
     */
    public static String dateToFile(LocalDateTime date) {
        return date.format(inputFormatter);
    }
}
