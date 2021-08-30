package duke.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

import duke.constants.Constants;

public class DateHelpers {
    /**
     * Reference
     * https://stackoverflow.com/questions/48280447/java-8-datetimeformatter-with-optional-part
     */
    public static LocalDateTime parseDateString(String input) {
        TemporalAccessor temporalAccessor = Constants.Input.DATETIME_FORMATTER.parseBest(input, LocalDateTime::from,
                LocalDate::from);
        assert temporalAccessor != null;
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        } else {
            return ((LocalDate) temporalAccessor).atStartOfDay();
        }
    }
}
