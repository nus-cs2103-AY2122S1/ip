package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date and a time.
 */
public class DateTime {

    private static final String SAVE_FILE_FORMAT = "MMM dd yyyy hh.mm a";
    private static final String COMMAND_LINE_FORMAT = "yyyy-MM-dd HHmm";

    private LocalDateTime dateTime;

    /**
     * Acts as the constructor for DateTime object.
     *
     * @param input the String with a date and time.
     */
    public DateTime(String input) throws DateTimeParseException {
        dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(COMMAND_LINE_FORMAT));
    }

    /**
     * Gets the string format for taskList.txt.
     *
     * @return The string format for taskList.txt.
     */
    public String getSaveFormat() {
        return dateTime.format(DateTimeFormatter.ofPattern(COMMAND_LINE_FORMAT));
    }

    /**
     * Outputs the DateTime in the following format.
     *
     * @return The DateTime in the following format.
     */
    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern(SAVE_FILE_FORMAT));
    }
}
