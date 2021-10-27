package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Class to properly extract time from the string input by user.
 */
public class TimeExtractor {
    /** Extracts time in a specific format.
     *
     * @param stringDate String of date in form yyyy-mm-dd.
     * @return String of date in form "MMM d yyyy".
     */
    public static String extractTime(String stringDate) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(stringDate);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date cannot been extracted properly.\n" 
                    + "Please follow yyyy-mm-dd format closely. :)");
        }
    }
}
