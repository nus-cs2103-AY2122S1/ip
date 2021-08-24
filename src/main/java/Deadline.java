import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final LocalDateTime by;

    public Deadline(String title, String byString) throws BlueException {
        super(title);
        try {
            by = LocalDateTime.parse(byString, formatter);
        } catch (DateTimeParseException e) {
            String message = "Please enter a valid date in the correct format, e.g., 2021-12-27T23:59:59";
            throw new BlueException(message);
        }
    }

    static String getClassRepr() {
        return "D";
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        String byRepr = by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return "[D]" + super.toString() + " (by: " + byRepr + ")";
    }
}
