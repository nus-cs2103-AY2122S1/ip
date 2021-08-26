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

public class Parser {

    // Helper function to parse a date from an input
    public static LocalDate parseDateFromInput(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
    }

    // Helper function to parse a time from and input
    public static LocalTime parseTimeFromInput(String timeString) throws DukeException {
        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time must be of the form HH:MM. (HH from 00-23, MM from 00-59)");
        }
    }

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

    // Helper function to separate a string into pieces (e.g. input into taskName and dateTime)
    public static String[] splitWith(String input, int startIndex, String regex, String errorMessage)
            throws DukeException {
        if (startIndex >= input.length() || !input.contains(regex)) {
            throw new DukeException(errorMessage);
        }
        return input.substring(startIndex).split(regex);
    }
}
