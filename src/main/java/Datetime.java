import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datetime {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate datetime;

    public Datetime(String datetimeString) {
        this.datetime = LocalDate.parse(datetimeString);
    };

    @Override
    public String toString() {
        return datetime.format(DATE_FORMATTER);
    }

}
