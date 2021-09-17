package duke;

import duke.Command.AddCommand;
import duke.Command.Command;
import duke.Command.DeleteCommand;
import duke.Command.DoneCommand;
import duke.Command.ExitCommand;
import duke.Command.FindCommand;
import duke.Command.ListCommand;

/**
 * Class with methods to parse user input
 */
public class Parser {
    /**
     * List of valid commands
     */
    public enum Type {
        bye,
        list,
        done,
        delete,
        find,
        todo,
        deadline,
        event,
        within
    }
    /**
     * Parses the input provided into relevant Commands
     *
     * @param input String of next line of user input
     * @return Relevant Command corresponding to input
     * @throws DukeException when invalid input
     */
    public static Command parse(String input) throws DukeException {
        Type firstWord = null;
        try {
            firstWord = Type.valueOf(input.split(" ")[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry, I don't understand what that means. :(");
        }
        assert firstWord != null;
        String errorMsg;
        switch (firstWord) {
        case bye:
            return new ExitCommand();
        case list:
            return new ListCommand();
        case done:
            errorMsg = "Sorry, please enter an integer after 'done'. (e.g. done 2)";
            int index = Integer.parseInt(secondParameter(input, errorMsg)) - 1;
            return new DoneCommand(index);
        case delete:
            errorMsg = "Sorry, please enter an integer after 'delete'. (e.g. delete 2)";
            int index2 = Integer.parseInt(secondParameter(input, errorMsg)) - 1;
            return new DeleteCommand(index2);
        case find:
            errorMsg = "Sorry, please enter a keyword after 'find'.";
            String remaining = secondParameter(input, errorMsg);
            return new FindCommand(remaining);
        default:
            errorMsg = "Sorry, tasks must include descriptions.";
            String remaining2 = secondParameter(input, errorMsg);
            return new AddCommand(firstWord, remaining2);
        }
    }
    /**
     * Gets the parameter after the command.
     * @param input Input to be parsed
     * @param error Error Message if nothing after command
     * @return String of second parameter
     * @throws DukeException when nothing after command
     */
    private static String secondParameter(String input, String error) throws DukeException {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(error);
        }
    }
}
