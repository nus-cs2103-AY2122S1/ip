package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm");

    protected LocalDate date;
    protected LocalTime time;

    DateTime(String date) {
        this.date = LocalDate.parse(date);
    }

    DateTime(String date, String time) {
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public static boolean isValidDate(String date) {
        try {
            DATE_FORMATTER.parse(date);
        } catch(DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidTime(String time) {
        try {
            TIME_FORMATTER.parse(time);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
