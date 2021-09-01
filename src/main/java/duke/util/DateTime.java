package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a date and a time
 */
public class DateTime {

    private static final String DATE_REGEX = "(\\d{4}-\\d{2}-\\d{2})";
    // Negative lookahead the dash so it doesn't capture the date
    private static final String TIME_REGEX = "(\\d{4})(?!-)(.+)?";

    private final LocalDateTime dateTime;

    /**
     * Constructor for DateTime object
     * @param input the String with a date and time
     */
    public DateTime(String input) throws DukeException {
        try {
            dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException err) {
            throw new DukeException(err.getMessage());
        }
    }

    /**
     * Gets the string format for taskList.txt
     *
     * @return The string format for taskList.txt.
     */
    public String getSaveFormat() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Outputs the DateTime in the following format
     *
     * @return The DateTime in the following format
     */
    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a"));
    }
}
