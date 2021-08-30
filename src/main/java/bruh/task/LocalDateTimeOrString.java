package bruh.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * A class which stores a date as a LocalDateTime object if provided
 * with correctly formatted string input, else simply as a string.
 */
public class LocalDateTimeOrString implements Serializable {
    private static final DateTimeFormatter IN_FORMATTER = DateTimeFormatter
            .ofPattern("" + "[dd-MM-yyyy HHmm]" + "[dd/MM/yyyy HHmm]");
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    private LocalDateTime dateTime;
    private String dateTimeString;

    /**
     * Constructor for an object which stores a date as a LocalDateTime object if provided
     * with correctly formatted string input, else simply as a string.
     *
     * @param dateTimeInput The string input to be parsed as a date & time, if possible.
     */
    public LocalDateTimeOrString(String dateTimeInput) {
        try {
            dateTime = LocalDateTime.parse(dateTimeInput, IN_FORMATTER);
        } catch (DateTimeParseException e) {
            dateTimeString = dateTimeInput;
        }
    }

    @Override
    public String toString() {
        return (dateTime != null) ? OUT_FORMATTER.format(dateTime) : dateTimeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (!(o instanceof LocalDateTimeOrString)) {
            return false;
        }
        LocalDateTimeOrString other = (LocalDateTimeOrString) o;
        return Objects.equals(dateTime, other.dateTime) && Objects.equals(dateTimeString, other.dateTimeString);
    }
}
