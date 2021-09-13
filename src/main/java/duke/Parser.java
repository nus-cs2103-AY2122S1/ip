package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Parses input from the user from a given string into arguments for other
 * parts of the program to process.
 */
public class Parser {

    /**
     * Parses a given string input from the user and returns the appropriate
     * command.
     *
     * @param command Input from the user.
     * @return Command to be executed.
     * @throws DukeException If input is invalid.
     */
    public static Command parse(String command) throws DukeException {

        String[] parsedArr = command.split("\\s", 2);

        switch(parsedArr[0]) {
        case "list":
            return checkList(parsedArr);
        case "todo":
            return checkTodo(parsedArr);
        case "deadline":
            return checkDeadline(parsedArr);
        case "event":
            return checkEvent(parsedArr);
        case "delete":
            return checkDelete(parsedArr);
        case "done":
            return checkDone(parsedArr);
        case "find":
            return checkFind(parsedArr);
        case "bye":
            return checkExit(parsedArr);
        default:
            throw new DukeException();
        }
    }

    /**
     * Parses <code>done</code> and <code>delete</code> commands in order to
     * extract task index from the user input.
     *
     * @param command The <code>done</code> or <code>delete</code> command.
     * @param rest The rest of the user's string input.
     * @return Command to be executed.
     * @throws DukeException If <code>rest</code> is not a legitimate integer.
     */
    private static Command parseFurther(String command, String rest) throws DukeException {

        int index;

        try {
            index = Integer.parseInt(rest) - 1;

            if (index < 0) {
                throw new InvalidIndexException();
            }
        } catch (NumberFormatException e) {
            throw new NoNumberException(command);
        }

        if (command.equals("done")) {
            return new DoneCommand(index);
        }

        if (command.equals("delete")) {
            return new DeleteCommand(index);
        }

        throw new DukeException("An unknown error has occurred.");
    }

    private static Command checkList(String[] strArr) throws DukeException {
        if (strArr.length > 1) {
            throw new DukeException();
        }

        return new ListCommand();
    }

    private static Command checkTodo(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new NoDescriptionException(strArr[0]);
        }

        return new AddCommand(strArr[0], strArr[1]);
    }

    private static Command checkDeadline(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new NoDescriptionException(strArr[0]);
        }

        if (!strArr[1].contains("/by")) {
            throw new NoKeywordException("/by");
        }

        return new AddCommand(strArr[0], strArr[1]);
    }

    private static Command checkEvent(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new NoDescriptionException(strArr[0]);
        }

        if (!strArr[1].contains("/at")) {
            throw new NoKeywordException("/at");
        }

        return new AddCommand(strArr[0], strArr[1]);
    }

    private static Command checkDelete(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new NoNumberException(strArr[0]);
        }

        return parseFurther(strArr[0], strArr[1]);
    }

    private static Command checkDone(String[] strArr) throws DukeException {
        if (strArr.length < 2) {
            throw new NoNumberException(strArr[0]);
        }

        return parseFurther(strArr[0], strArr[1]);
    }

    private static Command checkFind(String[] strArr) throws DukeException {
        if (strArr.length < 2 || strArr[1].isBlank()) {
            throw new EmptySearchQueryException();
        }

        return new FindCommand(strArr[1]);
    }

    private static Command checkExit(String[] strArr) throws DukeException {
        if (strArr.length > 1) {
            throw new DukeException();
        }

        return new ExitCommand();
    }
}
