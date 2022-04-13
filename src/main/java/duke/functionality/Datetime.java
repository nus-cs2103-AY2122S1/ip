package duke.functionality;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates date and time into one object.
 */
public class Datetime {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate datetime;
    private String datetimeString;

    /**
     * Returns a Datetime wrapper object that encapsulates a specified date/time.
     *
     * @param datetimeString The string form of the date/time that is to be parsed.
     */
    public Datetime(String datetimeString) {
        this.datetimeString = datetimeString;
        this.datetime = LocalDate.parse(datetimeString);
    };

    public String getDatetimeString() {
        return this.datetimeString;
    }

    @Override
    public String toString() {
        return datetime.format(DATE_FORMATTER);
    }

}
