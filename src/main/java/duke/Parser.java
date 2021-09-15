package duke;

import static java.lang.Integer.parseInt;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * This class represents a parser, which extracts the user's input into commands
 * for Duke.
 */
public class Parser {
    private final String commandWord;
    private final String arguments;
    private String dateString;

    /**
     * Constructor for a parser.
     *
     * @param input User input.
     */
    public Parser(String input) {
        String[] splitCommand = input.split(" ", 2);
        commandWord = splitCommand[0];
        arguments = splitCommand.length > 1 ? splitCommand[1] : null;
    }

    /**
     * Gets the command word that the user inputted.
     *
     * @return Command word.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Gets the number in the argument for done and delete commands.
     *
     * @return Array index provided by user.
     * @throws DukeException If no number is provided.
     */
    public int getNumber() throws DukeException {
        if (arguments == null) {
            throw new DukeException("No number provided.");
        }
        // Retrieve value inputted by user and subtract 1 to get the index in the array.
        return parseInt(arguments) - 1;
    }

    /**
     * Gets the search term in the argument for the find command
     *
     * @return Search term inputted by user.
     * @throws DukeException If no search term provided.
     */
    public String getSearchTerm() throws DukeException {
        if (arguments == null) {
            throw new DukeException("No search term provided.");
        }
        return arguments;
    }

    /**
     * Parses arguments if type is task.
     *
     * @return Task name.
     * @throws DukeException If name is not present.
     */
    public String getTaskName() throws DukeException {
        if (arguments == null) {
            throw new DukeException("Task needs to have a name.");
        }
        // Split message from due date
        String[] splitMessage = arguments.split("/", 2);
        dateString = splitMessage.length > 1 ? splitMessage[1] : null;
        return splitMessage[0];
    }

    /**
     * Parses task date to LocalDateTime.
     *
     * @return Task date.
     * @throws DukeException If date is invalid.
     */
    public LocalDateTime getTaskDate() throws DukeException {
        LocalDateTime date;
        // Error handling: no time provided.
        if (dateString == null) {
            throw new DukeException("Date cannot be empty.");
        }
        // Split date and time from "by" or "at"
        String[] splitDate = dateString.split(" ", 3);
        // Split day, month, year
        String[] splitDateElements = splitDate[1].split("/", 3);
        int day = parseInt(splitDateElements[0]);
        int month = parseInt(splitDateElements[1]);
        int year = parseInt(splitDateElements[2]);
        // Split hours and minutes
        int hours = parseInt(splitDate[2].substring(0, 2));
        int mins = parseInt(splitDate[2].substring(2, 4));
        try {
            date = LocalDateTime.of(year, month, day, hours, mins);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date.");
        }
        return date;
    }
}
