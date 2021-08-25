package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

/**
 * This class represents a parser, which extracts the user's input into commands
 * for Duke.
 */
public class Parser {
    private final String commandWord;
    private final String arguments;
    private String taskName;
    private String dateString;
    private LocalDateTime date;

    /**
     * Constructor for a parser.
     *
     * @param input User input.
     */
    public Parser(String input) {
        String[] splitCommand = input.split(" ", 2);
        this.commandWord = splitCommand[0];
        this.arguments = splitCommand.length > 1 ? splitCommand[1] : null;
    }

    /**
     * Gets the command word that the user inputted.
     *
     * @return Command word.
     */
    public String getCommandWord() {
        return this.commandWord;
    }

    /**
     * Gets the number in the argument for done and delete commands.
     *
     * @return Array index provided by user.
     * @throws DukeException If no number is provided.
     */
    public int getNumber() throws DukeException {
        if (this.arguments == null) {
            throw new DukeException("No number provided.");
        }
        // Retrieve value inputted by user and subtract 1 to get the index in the array.
        return parseInt(arguments) - 1;
    }

    /**
     * Parse arguments if type is task.
     *
     * @throws DukeException If name is not present.
     */
    public void parseTask() throws DukeException {
        if (this.arguments == null) {
            throw new DukeException("Task needs to have a name.");
        }
        // Split message from due date
        String[] splitMessage = this.arguments.split("/", 2);
        this.taskName = splitMessage[0];
        this.dateString = splitMessage.length > 1 ? splitMessage[1] : null;
    }

    /**
     * Parse task date to LocalDateTime.
     *
     * @throws DukeException If date is invalid.
     */
    public void parseDate() throws DukeException {
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
    }

    /**
     * Getter for the name of the task.
     *
     * @return Name of the task provided by user.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Getter for the date of the task.
     *
     * @return Date of the task inputted by user.
     */
    public LocalDateTime getDate() {
        return this.date;
    }
}
