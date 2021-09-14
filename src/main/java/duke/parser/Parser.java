package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.TagCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;
import duke.exception.MissingParameterDukeException;
import duke.exception.UnknownCommandDukeException;


/**
 * A Parser class that handles the input.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class Parser {

    /**
     * Process a given user input.
     * @param userCommand The user input.
     * @return a user command.
     * @throws DukeException unknown command syntax.
     */
    public static Command parse(String userCommand) throws DukeException{

        String[] command = userCommand.split(" ", 2);
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
                throw new MissingParameterDukeException(command[0]);
            }
            return new AddCommand(command[0], command[1]);
        case "done":
            if (noParameter) {
                throw new MissingParameterDukeException(command[0]);
            }
            return new DoneCommand(command[1]);
        case "tag":
            if (noParameter) {
                throw new MissingParameterDukeException(command[0]);
            }
            return new TagCommand(command[1]);
        case "delete":
            if (noParameter) {
                throw new MissingParameterDukeException(command[0]);
            }
            return new DeleteCommand(command[1]);
        case "find":
            if (noParameter) {
                throw new MissingParameterDukeException(command[0]);
            }
            return new FindCommand(command[1]);
        default:
            throw new UnknownCommandDukeException();
        }
    }
}
