package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.EmptyInputException;
import duke.exception.NotRecognizeException;
import duke.task.Task;

public class Parser {
    public Parser() {

    }

    /**
     * Returns boolean value which checks whether
     * input string is digit or not.
     *
     * @param input String input.
     * @return Content of input is digit or not.
     */
    public static boolean chekDigit(String input) {
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

    public static Command parse(String response) throws DukeException {
        int len = response.length();
        switch (response) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            default:
                Operation op = checkResponse(response, len);
                switch (op) {
                    case DEADLINE:
                        return new AddCommand(response, 2);
                    case TODO:
                        return new AddCommand(response, 1);
                    case EVENT:
                        return new AddCommand(response, 3);
                    case DONE:
                        return new MarkCommand(response);
                    case DELETE:
                        return new DeleteCommand(response);
                    case DATE:
                        return new SearchCommand(response, 1);
                    case FIND:
                        return new SearchCommand(response, 2);
                    default:
                        return null;
                }
        }
    }

    /**
     * Returns the correct enum operation according to response,
     * or it returns null to show exception occurred.
     *
     * @return Type of operation for the next judgement.
     */
    public static Operation checkResponse(String response, int len) throws DukeException {
        if (response.startsWith("find ") && len > 5) {
            return Operation.FIND;
        } else if (response.startsWith("date ")
                && Task.isDate(response.substring(5))) {
            return Operation.DATE;
        } else if (response.startsWith("done ")
                && chekDigit(response.substring(5))) {
            return Operation.DONE;
        } else if (response.startsWith("todo ") && len > 5) {
            return Operation.TODO;
        } else if (response.startsWith("deadline ")
                && response.substring(9).contains(" /by ")) {
            return Operation.DEADLINE;
        } else if (response.startsWith("event ")
                && response.substring(6).contains(" /at ")) {
            return Operation.EVENT;
        } else if (response.startsWith("delete ")
                && chekDigit(response.substring(7))) {
            return Operation.DELETE;
        } else if (response.equals("delete") || response.equals("todo") || response.equals("deadline")
                || response.equals("event") || response.equals("done") || response.equals("date")
                        || response.equals("find")) {
            String curr = response;
            throw new EmptyInputException(curr);
        } else {
            //This means there's no match of operations.
            throw new NotRecognizeException();
        }
    }
}
