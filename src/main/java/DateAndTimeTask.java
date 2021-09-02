import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateAndTimeTask extends Task {
    LocalDateTime dateTime;

    public DateAndTimeTask(String description) {
        super(description);
    }

    public DateAndTimeTask(String description, String details) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            dateTime = LocalDateTime.parse(details, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("is formatted date and time wrong. is please try again.\n" +
                    "format is yyyy-mm-dd hhmm (24h time)");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d LLL yyyy h.mma");
        String result = super.toString();
        if (dateTime != null) {
            result += " (" + dateTime.format(formatter) + ")";
        }
        return result;
    }
}
