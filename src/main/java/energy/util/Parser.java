package energy.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import energy.command.AddAliasCommand;
import energy.command.AddTaskCommand;
import energy.command.ByeCommand;
import energy.command.Command;
import energy.command.DeleteAliasCommand;
import energy.command.DeleteTaskCommand;
import energy.command.DoneCommand;
import energy.command.DueCommand;
import energy.command.FindCommand;
import energy.command.ListAliasCommand;
import energy.command.ListCommand;
import energy.command.OnDateCommand;
import energy.result.AliasHandler;

/**
 * A class that handles all parsing related functionality, parsing dates, times,
 * and commands from user input.
 */
public class Parser {
    private static AliasHandler aliasHandler = new AliasHandler();

    /**
     * Assigns an AliasHandler object that contains a list of aliases to Parser.
     *
     * @param aliasHandler An AliasHandler object containing a list of aliases.
     */
    public static void setAliasHandler(AliasHandler aliasHandler) {
        Parser.aliasHandler = aliasHandler;
    }

    /**
     * Gets an AliasHandler object assigned to Parser that contains a list of aliases.
     *
     * @return An AliasHandler object containing a list of aliases.
     */
    public static AliasHandler getAliasHandler() {
        return aliasHandler;
    }

    /**
     * Converts a string representing a date into a LocalDate object.
     *
     * @param dateString Date string that is to be converted to a LocalDate.
     * @return LocalDate converted from the input string.
     * @throws EnergyException If an invalid format or invalid date is given.
     */
    public static LocalDate parseDateFromInput(String dateString) throws EnergyException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new EnergyException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
    }

    /**
     * Converts a string representing a time into a LocalTime object.
     *
     * @param timeString Time string that is to be converted to a LocalTime.
     * @return LocalTime converted from the input string.
     * @throws EnergyException If an invalid format or invalid time is given.
     */
    public static LocalTime parseTimeFromInput(String timeString) throws EnergyException {
        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new EnergyException("Time must be of the form HH:MM. (HH from 00-23, MM from 00-59)");
        }
    }

    /**
     * Parses the user's input to determine what command is being called.
     * Command are not case sensitive. (e.g. bye is treated the same as BYE)
     *
     * @param input Input that contains the user's command.
     * @return Command object that can be used to execute the related function.
     * @throws EnergyException If an invalid command is given.
     */
    public static Command parseCommandFromInput(String input) throws EnergyException {
        String inputString = input.split(" ")[0].toLowerCase();
        String commandString = aliasHandler.convertAlias(inputString);
        // Note: any changes made to this switch-case should also be made
        // to the switch-case in AliasHandler method isCommandKeyword
        switch (commandString) {
        case "addalias":
            return new AddAliasCommand(input);
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "listalias":
            return new ListAliasCommand();
        case "done":
            return new DoneCommand(input);
        case "todo":
        case "deadline":
        case "event":
            return new AddTaskCommand(input);
        case "delete":
            return new DeleteTaskCommand(input);
        case "deletealias":
            return new DeleteAliasCommand(input);
        case "ondate":
            return new OnDateCommand(input);
        case "due":
            return new DueCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new EnergyException("You have entered an invalid command.");
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
     * @throws EnergyException If startIndex > length of input, or input does not contain the regex.
     */
    public static String[] splitWith(String input, int startIndex, String regex, String errorMessage)
            throws EnergyException {
        if (startIndex >= input.length() || !input.contains(regex)) {
            throw new EnergyException(errorMessage);
        }
        return input.substring(startIndex).split(regex);
    }

    /**
     * Combines a task name that has been split back into its original form.
     *
     * @param inputs     Input that contains the user's command.
     * @param startIndex The index at which the task name input starts.
     * @param endIndex The index after the last word of the task name.
     * @return A String containing the task name.
     */
    public static String parseTaskNameFromInput(String[] inputs, int startIndex, int endIndex) {
        StringBuilder taskName = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            taskName.append(inputs[i])
                    .append(" ");
        }
        return taskName.toString().trim();
    }
}
