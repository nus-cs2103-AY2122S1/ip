package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import duke.exception.DukeArgumentException;

public class DukeDate {
    private static final String DATE_INPUT_FORMAT = "d/MM/yyyy";
    private static final String DATE_OUTPUT_FORMAT = "E, d MMM yyyy";

    public static LocalDate parseDateInput(String dateStr) throws DukeArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeArgumentException(
                    String.format(
                            "\"%s\" is of an incorrect date format, the correct format is %s, like: %s",
                            dateStr,
                            DATE_INPUT_FORMAT,
                            formatter.format(LocalDate.now())
                    )
            );
        }
    }

    public static String formatDateSave(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);
        return formatter.format(date);
    }

    public static String formatDateOutput(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT, Locale.US);
        return formatter.format(date);
    }
}
