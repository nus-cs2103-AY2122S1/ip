package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a class that handles parsing of date-time inputs.
 */
public class DateTimeHandler {
    /**
     * Array of valid formats as Strings.
     */
    private final String[] FORMATS = {
        "dd-MM-yyyy hh:mm a",
        "dd-MM-yyyy HHmm",
        "MMM dd yyyy hh:mm a"
    };

    /**
     * Array of DateTimeFormatters converted from Strings in FORMATS.
     */
    private final DateTimeFormatter[] dtfList = new DateTimeFormatter[FORMATS.length];

    /**
     * Constructs a DateTimeHandler object and populates dtfList.
     */
    public DateTimeHandler() {
        assert FORMATS.length == dtfList.length : "DateTimeFormatter array should be same length as FORMATS array";
        for (int i = 0; i < FORMATS.length; i++) {
            dtfList[i] = DateTimeFormatter.ofPattern(FORMATS[i]);
        }
    }

    /**
     * Returns whether or not the input string can be parsed by the given formatter.
     *
     * @param dtf The DateTimeFormatter object.
     * @param s The string to be parsed.
     * @return Whether or not the string can be parsed.
     */
    private boolean tryToParse(DateTimeFormatter dtf, String s) {
        try {
            LocalDateTime.parse(s, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a LocalDateTime if able to parse the given string using one of the DateTimeFormatters in dtfList.
     *
     * @param s String to be parsed.
     * @return The created LocalDateTime object if the string can be parsed, null if it cannot be parsed.
     */
    public LocalDateTime parseDate(String s) {
        for (DateTimeFormatter dateTimeFormatter : dtfList) {
            if (tryToParse(dateTimeFormatter, s)) {
                return LocalDateTime.parse(s, dateTimeFormatter);
            }
        }
        return null;
    }

    /**
     * Returns a String array of valid date formats.
     *
     * @return The String array containing the valid date formats.
     */
    public String[] getFormatList() {
        String[] res = new String[FORMATS.length + 1];
        res[0] = "Valid date formats:";
        System.arraycopy(FORMATS, 0, res, 1, FORMATS.length);
        return res;
    }

    /**
     * Returns a String to be printed when an invalid format is entered.
     *
     * @return The String to be printed.
     */
    public String invalidFormat() {
        return "Please enter a valid date-time format. Type formats to see valid formats";
    }

}
