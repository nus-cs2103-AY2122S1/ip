package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.TaskList;

/**
 * Carries out a certain action depending on user input.
 */
public abstract class Command {

    /**
     * Original user input.
     */
    protected final String input;

    Command(String input) {
        this.input = input;
    }
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

    public abstract String execute(TaskList tasks);
}
