import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeManager {
    DateTimeFormatter formatter;

    public DateTimeManager(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public LocalDate parseDateTime(String dateTime) {
        LocalDate date = LocalDate.parse(dateTime, formatter);
        return date;
    }
}
