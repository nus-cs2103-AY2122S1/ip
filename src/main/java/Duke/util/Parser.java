package Duke.util;

import Duke.command.*;
import Duke.exception.DukeException;
import Duke.exception.InvalidArgumentException;
import Duke.exception.InvalidCommandException;
import Duke.exception.MissingArgumentException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-JUnit. Add JUNit Test
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

        // null or empty
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
     * @throws MissingArgumentException throws a Duke.util.Duke.exception.MissingArgumentException if no description found
     */
    private static String checkDescription(String[] strArr, String event) throws MissingArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("description", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("description", event);
        } else {
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

    /**
     * Parses the inputted command and creates the corresponding command
     *
     * @param command String containing the inputted command
     * @return Command Returns the corresponding command
     * @throws DukeException throws a Duke Exception that might be raised during the parsing of the inputted
     *                       command
     */
    public static Command parseCommandString(String command) throws DukeException {
        Command currentCommand = new InvalidCommand();
        String[] checkCommand = command.split(" ", 2);
        switch(checkCommand[0]) {
            case "todo":
                currentCommand = new AddCommand(checkDescription(checkCommand, "todo"), "todo");
                break;
            case "event":
                currentCommand = new AddCommand(checkDescription(checkCommand, "event"), "event");
                break;
            case "deadline":
                currentCommand = new AddCommand(checkDescription(checkCommand, "deadline"), "deadline");
                break;
            case "delete":
                int deleteTaskNumber = checkInteger(checkCommand, "deleting task");
                currentCommand = new DeleteCommand(deleteTaskNumber);
                break;
            case "done":
                int doneTaskNumber = checkInteger(checkCommand, "marking of task");
                currentCommand = new MarkCommand(doneTaskNumber);
                break;
            case "list":
                currentCommand = new ListCommand();
                break;
            case "help":
                currentCommand = new HelpCommand();
                break;
            case "bye":
                currentCommand = new ExitCommand();
                break;
            default:
                throw new InvalidCommandException();
        }
        return currentCommand;

    }

}
