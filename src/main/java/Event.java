import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final LocalDateTime at;

    public Event(String title, String atString) throws BlueException {
        super(title);
        try {
            at = LocalDateTime.parse(atString, formatter);
        } catch (DateTimeParseException e) {
            String message = "Please enter a valid date in the correct format, e.g., 2021-12-27T23:59:59";
            throw new BlueException(message);
        }
    }

    static String getClassRepr() {
        return "E";
    }

    public String getAt() {
        return at;
    }

    public String toString() {
        String atRepr = at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return "[E]" + super.toString() + " (at: " + atRepr + ")";
    }
}
