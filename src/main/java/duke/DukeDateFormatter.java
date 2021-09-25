package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateFormatter {
    // DateTimeFormatter is immutable and thread-safe, and thus the recommended
    // approach is to store it in a static constant.
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }

    public static String dateTimeToFile(LocalDateTime dateTime) {
        return dateTime.format(inputFormatter);
    }

    public static LocalDateTime stringToDateTime(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, inputFormatter);
    }
}
