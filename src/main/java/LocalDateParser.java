import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateParser {

    public static LocalDate parse(String str) throws IllegalArgumentException {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date could not be parsed!");
        }
        return date;
    }
}
