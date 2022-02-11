package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to handle the logic of parsing, with static fields to provide regex expr.
 */
public class DateParser {

    public static final String REGEX_DATE = "\\d{4}-\\d{2}-\\d{2}";
    public static final String REGEX_TIME = "\\d{4}";

    /**
     * Parses date time strings in the form of YYYY-MM-DD HHMM where the time is optional.
     * If the format is not expected, the LocalDateTime.parse function will fail and throw
     * DateTimeParseException.
     *
     * @param dateTimeString YYY-MM-DD HHMM (Time in 24 HR form, but optional)
     * @return LocalDateTime object
     * @throws DateTimeParseException runtime exception when handling an invalid input.
     */
    public static LocalDateTime parseDateTimeInput(String dateTimeString)
        throws DateTimeParseException {
        Matcher dateMatch = Pattern.compile(REGEX_DATE).matcher(dateTimeString);
        Matcher timeMatch = Pattern.compile(REGEX_TIME).matcher(dateTimeString);

        String isoDateTime;
        if (!dateMatch.find()) {
            return LocalDateTime.parse("");
        }
        isoDateTime = dateMatch.group();

        String timePortion = "0000"; // default time is midnight
        if (timeMatch.find(dateMatch.end())) {
            timePortion = timeMatch.group();
        }
        isoDateTime += "T" + timePortion.substring(0, 2) + ":" + timePortion.substring(2) + ":00";
        return LocalDateTime.parse(isoDateTime);
    }

    /**
     * Convert dates into a String form in readable, pretty standardised date format.
     * Use replace to ensure consistency (Github containers seem to output AM while
     * local dev computer outputs am).
     *
     * @param dateTime object representing date.
     * @return dateString
     */
    public static String toHumanReadable(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
        return dateTime.format(dtf).replace("am", "AM").replace("pm", "PM");
    }

    /**
     * Convert dates into a String form in database ready standardised date format.
     *
     * @param dateTime object representing date.
     * @return dateString
     */
    public static String toDatabaseFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
