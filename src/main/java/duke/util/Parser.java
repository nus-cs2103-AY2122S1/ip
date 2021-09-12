package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.MissingArgumentException;
import duke.exception.TooManyArgumentsException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Encapsulates the parser where it parses the inputted command from the user and runs
 * the correct command
 *
 * @author Keith Tan
 */
public class Parser {

    /**
     * Checks whether given string is an integer
     *
     * @param str string to determine whether it is an integer
     * @return boolean returns true if string is an integer and
     *         returns false otherwise
     */
    private static boolean isNumeric(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether user inputted the description for the task
     *
     * @param strArr String array containing the command
     * @param event String stating the type of task to be added
     * @return String Returns the description of the task
     * @throws MissingArgumentException throws a MissingArgumentException if no description found
     *
     */
    private static String checkDescription(String[] strArr, String event) throws MissingArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("description", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("description", event);
        } else {
            assert strArr[1].trim().length() > 0 : "Description is not missing or just empty string";
            return strArr[1];
        }

    }

    /**
     * Checks whether user inputted the description for the task
     *
     * @param strArr String array containing the command
     * @param event String stating the type of task to be added
     * @return String Returns the description of the task
     * @throws MissingArgumentException throws a MissingArgumentException if no description found
     */
    private static String checkSearchTerm(String[] strArr, String event) throws MissingArgumentException,
            TooManyArgumentsException {

        assert event.equals("filter") : "Error occurred in searching for term";
        if (strArr.length < 2) {
            throw new MissingArgumentException("search term", event);
        } else if (strArr.length > 2) {
            throw new TooManyArgumentsException("search terms", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("search term", event);
        } else {
            assert strArr[1].trim().length() == 1 : "Search term is not missing, just empty string and only 1 word";
            return strArr[1];
        }

    }

    /**
     * Checks whether user inputted an appropriate integer to mark the task
     *
     * @param strArr String array containing the integer
     * @param event String stating whether task is to be marked or deleted
     * @return int Returns the task number to be marked
     * @throws MissingArgumentException throws a Duke.util.Duke.exception.MissingArgumentException if no integer found
     * @throws InvalidArgumentException throws a Duke.util.Duke.exception.InvalidArgumentException if no integer
     *                                  inputted after done command
     */
    private static int checkInteger(String[] strArr, String event) throws MissingArgumentException,
            InvalidArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("integer", event);
        } else if (!isNumeric(strArr[1])) {
            throw new InvalidArgumentException("integer", event);
        } else {
            return Integer.parseInt(strArr[1]);
        }

    }

    private static String[] parseUpdateString(String updateDetails) throws MissingArgumentException,
        InvalidArgumentException {

        String[] commandTerms = updateDetails.split(" ", 3);
        if (commandTerms.length < 3) {

            throw new MissingArgumentException("commands", "update");

        } else if (!isNumeric(commandTerms[0])) {

            throw new InvalidArgumentException("integer", "update");

        } else if (commandTerms[1].toLowerCase().equals("edt") || commandTerms[1].toLowerCase().equals("sdt")
            || commandTerms[1].toLowerCase().equals("desc")) {

            commandTerms[1] = commandTerms[1].toLowerCase();
            return commandTerms;

        } else {

            throw new InvalidArgumentException("update field", "update");

        }

    }

    /**
     * Parses the inputted command and creates the corresponding command
     *
     * @param command String containing the inputted command
     * @return Command Returns the corresponding command
     * @throws DukeException throws a Duke Exception that might be raised during the parsing of the inputted
     *                       command
     */
    public static Command parseCommandString(String command, Tasklist currentTaskList, Store storage)
        throws DukeException {
        Command currentCommand = new InvalidCommand();
        String[] checkCommand = command.split(" ", 2);
        switch(checkCommand[0]) {
        case "todo":
            currentCommand = new AddCommand(currentTaskList, checkDescription(checkCommand, "todo"), "todo");
            break;
        case "event":
            currentCommand = new AddCommand(currentTaskList, checkDescription(checkCommand, "event"), "event");
            break;
        case "deadline":
            currentCommand = new AddCommand(currentTaskList, checkDescription(checkCommand, "deadline"), "deadline");
            break;
        case "delete":
            int deleteTaskNumber = checkInteger(checkCommand, "deleting task");
            currentCommand = new DeleteCommand(currentTaskList, deleteTaskNumber);
            break;
        case "done":
            int doneTaskNumber = checkInteger(checkCommand, "marking of task");
            currentCommand = new MarkCommand(currentTaskList, doneTaskNumber);
            break;
        case "update":
            String[] updateTerms = parseUpdateString(checkCommand[1]);
            currentCommand = new UpdateCommand(currentTaskList, updateTerms);
            break;
        case "list":
            currentCommand = new ListCommand(currentTaskList);
            break;
        case "find":
            currentCommand = new FindCommand(currentTaskList, checkSearchTerm(command.split(" "), "filter"));
            break;
        case "help":
            currentCommand = new HelpCommand();
            break;
        case "bye":
            storage.saveTasksToStore(currentTaskList);
            currentCommand = new ExitCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return currentCommand;

    }

}
