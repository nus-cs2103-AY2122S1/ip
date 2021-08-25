package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command abstract class
 */
public abstract class Command {
    protected String commandName;
    protected String description;

    public abstract void execute();

    protected static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
