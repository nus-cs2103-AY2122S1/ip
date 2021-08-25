package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private DateTimeFormatter format;

    public DateValidator(DateTimeFormatter format) {
        this.format = format;
    }

    public boolean isValid(String string) {
        try {
            LocalDate.parse(string, format);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}
