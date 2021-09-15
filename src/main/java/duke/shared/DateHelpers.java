package duke.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import duke.constants.Constants;

public class DateHelpers {
    //@@author sunjc826-reused
    //Reused from https://stackoverflow.com/questions/48280447/java-8-datetimeformatter-with-optional-part

    /**
     * Parses and creates a datetime object.
     * @param input String formatted to represent a date and time.
     * @return LocalDateTime object.
     */
    public static LocalDateTime parseDateString(String input) {
        try {
            TemporalAccessor temporalAccessor = Constants.Input.DATETIME_FORMATTER.parseBest(input, LocalDateTime::from,
                    LocalDate::from);
            assert temporalAccessor != null;
            if (temporalAccessor instanceof LocalDateTime) {
                return (LocalDateTime) temporalAccessor;
            } else {
                return ((LocalDate) temporalAccessor).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Formatting not recognized.");
        }
    }
    //@@author
}
