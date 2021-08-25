import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class Command {

    protected final String input;

    /**
     * Parses a date String into LocalDate.
     *
     * Uses the ISO_LOCAL_DATE format (e.g 1970-09-21) to parse a date.
     * @param date String containing the date
     * @return LocalDate corresponding to the date in the String
     */
    protected static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DukeException(date + " is not a valid date!");
        }
    }

    Command(String input) {
        this.input = input;
    }

    abstract public boolean execute(TaskList tasks, Ui ui);
}
