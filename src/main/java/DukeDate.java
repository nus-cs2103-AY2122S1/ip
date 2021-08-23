import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DukeDate {
    private static String dateInputFormat = "d/MM/yyyy";
    private static String dateOutputFormat = "E, d MMM yyyy";

    public static LocalDate parseDateInput(String dateStr) throws DukeArgumentException{

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateInputFormat);
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeArgumentException(
                    String.format(
                            "\"%s\" is of an incorrect date format, the correct format is %s, like: %s",
                            dateStr,
                            dateInputFormat,
                            formatter.format(LocalDate.now())
                    )
            );
        }
    }

    public static String formatDateOutput(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateOutputFormat, Locale.US);
        return formatter.format(date);
    }
}
