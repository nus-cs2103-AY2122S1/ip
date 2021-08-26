package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DueCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.OnDateCommand;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeParseException;

/**
 * A class that handles all parsing related functionality, parsing dates, times,
 * and commands from user input.
 */
public class Parser {

    /**
     * Converts a string representing a date into a LocalDate object.
     *
     * @param dateString Date string that is to be converted to a LocalDate.
     * @return LocalDate converted from the input string.
     * @throws DukeException If an invalid format or invalid date is given.
     */
    public static LocalDate parseDateFromInput(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
    }

    /**
     * Converts a string representing a time into a LocalTime object.
     *
     * @param timeString Time string that is to be converted to a LocalTime.
     * @return LocalTime converted from the input string.
     * @throws DukeException If an invalid format or invalid time is given.
     */
    public static LocalTime parseTimeFromInput(String timeString) throws DukeException {
        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time must be of the form HH:MM. (HH from 00-23, MM from 00-59)");
        }
    }

    /**
     * Parses the user's input to determine what command is being called.
     *
     * @param input Input that contains the user's command.
     * @return Command object that can be used to execute the related function.
     * @throws DukeException If an invalid command is given.
     */
    public static Command parseCommandFromInput(String input) throws DukeException {
        String commandString = input.split(" ")[0].toUpperCase();
        switch (commandString) {
        case "BYE":
            return new ByeCommand();
        case "LIST":
            return new ListCommand();
        case "DONE":
            return new DoneCommand(input);
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            return new AddCommand(input);
        case "DELETE":
            return new DeleteCommand(input);
        case "ONDATE":
            return new OnDateCommand(input);
        case "DUE":
            return new DueCommand(input);
        case "FIND":
            return new FindCommand(input);
        default:
            throw new DukeException("You have entered an invalid command.");
        }
    }

    /**
     * Splits a string into a string array depending on a regular expression.
     *
     * @param input        Input that contains the user's command.
     * @param startIndex   Location to start splitting from.
     * @param regex        Regular expression that is used to split the string into pieces.
     * @param errorMessage Error message to be displayed if the input is invalid.
     * @return Command object that can be used to execute the related function.
     * @throws DukeException If startIndex > length of input, or input does not contain the regex.
     */
    public static String[] splitWith(String input, int startIndex, String regex, String errorMessage)
            throws DukeException {
        if (startIndex >= input.length() || !input.contains(regex)) {
            throw new DukeException(errorMessage);
        }
        return input.substring(startIndex).split(regex);
    }
}
