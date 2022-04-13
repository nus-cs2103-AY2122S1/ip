package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

    private DateFormatter() {}

    /**
     * Formats date to use MMM d yyyy pattern
     *
     * @param date
     * @return Formatted Date.
     */
    public static String formatDate(String date) {
        String outputDate = "";
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            outputDate =
                parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            outputDate = date;
        }
        return outputDate;
    }
}
