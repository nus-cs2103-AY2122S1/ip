package tasks;

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
    public static final String REGEX_VALID_INPUT = REGEX_DATE + "(\\s+" + REGEX_TIME + ")?";

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

    public static String toHumanReadable(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));
    }

    public static String toDatabaseFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
/*
Notes for REGEX:

(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])
1900-01-01 to 2099-12-31, with space, dot, slash or hyphen as separators

([0-2]?\d|3[0-1])[- /][a-zA-Z]{3,}[- /](19|20)\d{2}
2-xYz
2-xyZ
2-Xyz
02-XYZ to 31-XYZ
followed by another separator then a year (1900-2099)
separator can be space, hyphen or forward slash

(([01]\d|2[0-3])[0-5]\d)
0000 - 2359

(0?[1-9]|1[0-2])[:.]?([0-5][0-9])?\s?(pm|am|PM|AM)
2 PM
2.30 PM
02.30 PM
02:30 PM
hours from 1-12, minutes from 00 - 59
followed by AM/PM/pm/am, preceding space not necessary.
 */
