package duke.io;

import java.util.Scanner;

import duke.Duke;
import duke.DukeException;
import duke.io.Command.CommandName;


/**
 * Parser class intended to be used to parse user input to extract the correct command.
 */
public class Parser {

    /**
     * Retrieves the next line of input, disregarding extra whitespaces.
     *
     * @param sc The scanner object to retrieve the next line of input from
     * @return The next line of input after trimming whitespace from both ends
     */
    public static String getInput(Scanner sc) {
        return sc.nextLine().trim();
    }

    /**
     * Parses a line of given user input to extract the relevant/correct command.
     *
     * @param userInput A String containing a line of input to parse as a single command
     * @return A Command object containing an identifier of the command and its arguments
     * @throws DukeException When given input cannot be parsed successfully
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            // EXIT command
            return parseBye(userInput);
        } else if (userInput.equals("list")) {
            // LIST command
            return parseList(userInput);
        } else if (userInput.startsWith("done")) {
            // DONE command
            return parseDone(userInput);
        } else if (userInput.startsWith("delete")) {
            // DELETE command
            return parseDelete(userInput);
        } else if (userInput.startsWith("todo")) {
            // TODO command
            return parseTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            // DEADLINE command
            return parseDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            // EVENT command
            return parseEvent(userInput);
        } else if (userInput.startsWith("dowithinperiod")) {
            // DOWITHINPERIOD command
            return parseDoWithinPeriod(userInput);
        }else if (userInput.startsWith("date")) {
            // DATE command
            return parseDate(userInput);
        } else if (userInput.startsWith("find")) {
            // FIND command
            return parseFind(userInput);
        } else {
            // Unknown command
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a BYE command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the BYE command
     */
    protected static Command parseBye(String userInput) {
        return new Command(CommandName.BYE);
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a LIST command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the LIST command
     */
    protected static Command parseList(String userInput) {
        return new Command(CommandName.LIST);
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a DONE command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the DONE command
     */
    protected static Command parseDone(String userInput) throws DukeException {
        if (userInput.length() == 4) {
            throw new DukeException("A Done command should be accompanied by the index of the Task to mark as done.");
        } else if (!userInput.startsWith("done ")) {
            // If it does not start with "done " after trimming, it is an invalid word
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        int index = Integer.parseInt(userInput.substring(5));
        return new Command(CommandName.DONE, new String[]{String.valueOf(index)});
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a DELETE command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the DELETE command
     */
    protected static Command parseDelete(String userInput) throws DukeException {
        if (userInput.length() == 6) {
            throw new DukeException("A Delete command should be accompanied by the index of the Task to delete.");
        } else if (!userInput.startsWith("delete ")) {
            // If it does not start with "done " after trimming, it is an invalid word
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        int index = Integer.parseInt(userInput.substring(7));
        return new Command(CommandName.DELETE, new String[]{String.valueOf(index)});
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a TODO command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the TODO command (If the input is valid)
     * @throws DukeException An exception describing why the TODO command was unable to be parsed
     */
    protected static Command parseTodo(String userInput) throws DukeException {
        // If no arguments provided
        if (userInput.length() == 4) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else if (!userInput.startsWith("todo ")) {
            // If it does not start with "todo " after trimming, it is an invalid word
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        // Command name + description only
        return new Command(CommandName.TODO, new String[]{userInput.substring(5).trim()});
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a DEADLINE command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the DEADLINE command (If the input is valid)
     * @throws DukeException An exception describing why the DEADLINE command was unable to be parsed
     */
    protected static Command parseDeadline(String userInput) throws DukeException {
        // If no arguments provided
        if (userInput.length() == 8) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!userInput.startsWith("deadline ")) {
            // If it does not start with "deadline " after trimming, it is an invalid word
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (!userInput.contains("/by ")) {
            throw new DukeException("A deadline must contain a deadline indicated after the /by command");
        }

        String description = userInput.substring(9, userInput.indexOf("/by")).trim();
        String deadline = userInput.substring(userInput.indexOf("/by")).trim().substring(4);
        if (description.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty");
        }

        // Command name + description + deadline
        return new Command(CommandName.DEADLINE, new String[]{description, deadline});
    }

    /**
     * Parses a string expected to be a line of command + its arguments for an EVENT command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the EVENT command (If the input is valid)
     * @throws DukeException An exception describing why the EVENT command was unable to be parsed
     */
    protected static Command parseEvent(String userInput) throws DukeException {
        // If no arguments provided
        if (userInput.length() == 5) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (!userInput.startsWith("event ")) {
            // If it does not start with "deadline " after trimming, it is an invalid word
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (!userInput.contains("/at ")) {
            throw new DukeException("An event must contain a datetime indicated after the /at command");
        }

        String description = userInput.substring(6, userInput.indexOf("/at")).trim();
        String dateTime = userInput.substring(userInput.indexOf("/at")).trim().substring(4);
        if (description.length() == 0) {
            throw new DukeException("The description of an event cannot be empty");
        }

        // Command name + description + dateTime
        return new Command(CommandName.EVENT, new String[]{description, dateTime});
    }

    protected static Command parseDoWithinPeriod(String userInput) throws DukeException {
        if (userInput.length() == 14) {
            throw new DukeException("A start and end date must be provided to specify the period to complete the task");
        } else if(!userInput.startsWith("dowithinperiod ")) {
            // If it does not start with "dowithinperiod " after trimming, it is an invalid command
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (!userInput.contains("/between ")) {
            throw new DukeException("A DoWithinPeriodTask must contain a start of time period indicated after the /between command");
        } else if (!userInput.contains("/and ")) {
            throw new DukeException("A DoWithinPeriodTask must contains an end of time period indicated after the /and command");
        }

        String description = userInput.substring(15, userInput.indexOf("/between")).trim();
        String startOfPeriod = userInput.substring(userInput.indexOf("/between"), userInput.indexOf("/and")).substring(9).trim();
        String endOfPeriod = userInput.substring(userInput.indexOf("/and")).substring(5).trim();
        if (description.length() == 0) {
            throw new DukeException("The description of an event cannot be empty");
        }

        // Command name + description + startOfPeriod + endOfPeriod
        return new Command(CommandName.DOWITHINPERIOD, new String[]{description, startOfPeriod, endOfPeriod});
    }

    /**
     * Parses a string expected to be a line of command + its arguments for a DATE command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the DATE command (If the input is valid)
     * @throws DukeException An exception describing why the DATE command was unable to be parsed
     */
    protected static Command parseDate(String userInput) throws DukeException {
        if (userInput.length() == 4) {
            throw new DukeException(
                    "A date must be provided to find events and deadlines occurring on that day");
        } else if (!userInput.startsWith("date ")) {
            // If it does not start with "date " after trimming, it is an invalid command
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        // Command name + date only
        return new Command(CommandName.DATE, new String[]{userInput.substring(5)});
    }

    /**
     *
     * Parses a string expected to be a line of command + its arguments for a FIND command
     *
     * @param userInput A line of characters from the user
     * @return A command representing the FIND command (If the input is valid)
     * @throws DukeException An exception describing why the FIND command was unable to be parsed
     */
    protected static Command parseFind(String userInput) throws DukeException {
        if (userInput.length() == 4) {
            throw new DukeException("A keyword must be input for the FIND command to search through your tasks");
        } else if (!userInput.startsWith("find ")) {
            // If it does not start with "find " after trimming, it is an invalid command
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return new Command(CommandName.FIND, new String[]{userInput.substring(5)});
    }
}
