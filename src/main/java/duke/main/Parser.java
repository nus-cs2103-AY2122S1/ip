package duke.main;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;

/**
 * Represents a abstraction to read user input.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Parser {
    private Parser() {
    }

    /**
     * Takes in a user duke.command, logic decides which duke.command to call.
     *
     * @param command a string of user input duke.command.
     * @return a duke.command object to execute the user duke.command.
     * @throws DukeException exception handled by DukeException class.
     */
    public static Command parse(String command) throws DukeException {
        String[] commandDescription = command.split(" ", 0);
        String action = commandDescription[0];
        try {
            switch (action) {
            case "done":
                return new MarkDoneCommand(Integer.parseInt(commandDescription[1]));
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(Integer.parseInt(commandDescription[1]));
            case "filter":
                return new FilterCommand(commandDescription[1]);
            case "find":
                return new FindCommand(command);
            case "bye":
                return new ExitCommand();
            default:
                return new AddCommand(command);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException(e);
        }
    }
}
