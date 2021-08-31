package duke.parser;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.Command;
import duke.exception.DukeException;


/**
 * A Parser class that handles the input.
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class Parser {

    /**
     * Process a given user input
     *
     * @param userCommand The user input
     */
    public static Command parse(String userCommand) throws DukeException{

        String[] command = userCommand.split(" ", 2);
        DukeException missingParamException =
                new DukeException(String.format("OOPS!!! Parameter for \"%s\" is missing", command[0]));
        boolean noParameter = command.length < 2;
        switch (command[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
        case "deadline":
        case "event":
            if (noParameter) {
                throw missingParamException;
            }
            return new AddCommand(command[0], command[1]);
        case "done":
            if (noParameter) {
                throw missingParamException;
            }
            return new DoneCommand(command[1]);
        case "delete":
            if (noParameter) {
                throw missingParamException;
            }
            return new DeleteCommand(command[1]);
        case "find":
            if (noParameter) {
                throw missingParamException;
            }
            return new FindCommand(command[1]);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
