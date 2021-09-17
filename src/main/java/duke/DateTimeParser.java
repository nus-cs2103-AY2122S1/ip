package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A class representing a parser that returns a date.
 */
public class DateTimeParser extends Parser<LocalDate> {
    private final ArrayList<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();

    /**
     * A constructor that takes in accepted date formats.
     *
     * @param dateFormats Accepted date formats for the parser.
     */
    public DateTimeParser(String[] dateFormats) {
        for (String format : dateFormats) {
            dateTimeFormatters.add(DateTimeFormatter.ofPattern(format));
        }
    }

    /**
     * Attempts to parse the string as a date. If unable to, null is returned.
     *
     * @param s The string to be parsed.
     * @return A date if the string can be parsed as a date, null otherwise.
     */
    public LocalDate parse(String s) {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {
                // Try different formats and catch when unable to do so, hence no action required
            }
        }
        return null;
    }
}
