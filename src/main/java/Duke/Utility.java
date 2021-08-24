package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Utility {
    private final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public static LocalDateTime parseDate(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, inputFormatter);
    }

    public static String dateToString(LocalDateTime date) {
        return date.format(outputFormatter);
    }

    public static String dateToFile(LocalDateTime date) {
        return date.format(inputFormatter);
    }
}
