package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateString {
    private final String dateString;
    private LocalDate localDate;

    public DateString(String str) {
        this.dateString = str;
        try {
            this.localDate = LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            this.localDate = null;
        }
    }

    public String toString() {
        if (this.localDate != null) {
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.dateString;
        }
    }

}
