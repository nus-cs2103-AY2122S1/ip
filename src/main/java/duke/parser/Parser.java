package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.Operation;
import duke.command.SearchCommand;
import duke.exception.DukeException;
import duke.exception.EmptyInputException;
import duke.exception.MismatchedFormException;
import duke.exception.NotRecognizeException;

/**
 * Represents the class dealing with all types of command.
 */
public class Parser {
    /**
     * Returns boolean value which checks whether
     * input string is digit or not.
     *
     * @param input String input.
     * @return Content of input is digit or not.
     */
    public static boolean checkIsDigit(String input) {
        boolean isDigit = true;
        int i = 0;
        if (input.charAt(0) == '-') {
            i = 1;
        }
        for (; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (!(curr >= '0' && curr <= '9')) {
                isDigit = false;
                break;
            }
        }
        return isDigit;
    }

    /**
     * Checks whether the required key word is in user input or not.
     *
     * @param response    The user input.
     * @param splitIndex  The index where string splits apart.
     * @param splitString The string specifies how to split.
     * @return The content part.
     * @throws MismatchedFormException Deadline doesn't contain /by and event doesn't contain /at.
     */
    public static String checkContent(String response,
                                      int splitIndex, String splitString) throws MismatchedFormException {
        String content = response.substring(splitIndex);
        boolean isContain = content.contains(splitString);
        if (splitString.equals(" /by ") && !isContain) {
            throw new MismatchedFormException("deadline", " /by ");
        } else if (splitString.equals(" /at ") && !isContain) {
            throw new MismatchedFormException("event", " /at ");
        } else {
            return content;
        }
    }

    /**
     * Transfers the user input to correct command.
     *
     * @param response The user input.
     * @return The corresponding command.
     * @throws DukeException The exception related to duke.
     */
    public static Command parse(String response) throws DukeException {
        //Solution below adapted from https://github.com/nus-cs2103-AY2122S1/ip/pull/13/files
        if (response.equals("bye")) {
            return new ExitCommand();
        } else if (response.equals("list")) {
            return new ListCommand();
        } else if (isEmpty(response)) {
            throw new EmptyInputException(response);
        } else if (response.matches("^todo( .*)?")) {
            return new AddCommand(response, Operation.TODO, 5);
        } else if (response.matches("^deadline( .*)?")) {
            return new AddCommand(response, Operation.DEADLINE, 9, " /by ");
        } else if (response.matches("^event( .*)?")) {
            return new AddCommand(response, Operation.EVENT, 6, " /at ");
        } else if (response.matches("^done( .*)?")) {
            return new MarkCommand(response, 5);
        } else if (response.matches("^delete( .*)?")) {
            return new DeleteCommand(response, 7);
        } else if (response.matches("^find( .*)?")) {
            return new SearchCommand(response, Operation.FIND, 5);
        } else if (response.matches("^date( .*)?")) {
            return new SearchCommand(response, Operation.DATE, 5);
        } else {
            throw new NotRecognizeException();
        }
    }

    /**
     * Checks whether the command is empty with only part of command.
     *
     * @param response The user input.
     * @return Whether the command is empty or not.
     */
    public static boolean isEmpty(String response) {
        return response.trim().equals("delete") || response.equals("todo") || response.equals("deadline")
                || response.equals("event") || response.equals("done") || response.equals("date")
                || response.equals("find");
    }
}
