package duke.parser;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.data.exception.DukeException;
import duke.data.exception.InvalidCommandException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    public Parser() {}

    /** All Possible commands */
    private enum Commands {
        LIST, TODO, DEADLINE, EVENT, DONE, DELETE, BYE, FIND, HELP
    }

    /**
     * Converts user input into a Command
     *
     * @param fullCommand the user input
     * @return the Command encapsulating the given user input
     * @throws InvalidCommandException if command is invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        //Extract command
        int i = fullCommand.indexOf(" ");
        boolean isSingleWordInput = i == -1;
        try {
            Commands cmd = Commands.valueOf(
                    isSingleWordInput
                    ? fullCommand.toUpperCase()
                    : fullCommand.substring(0, i).toUpperCase());
            String rest = isSingleWordInput ? "" : fullCommand.substring(i).strip();

            switch (cmd) {
            case LIST:
                return new ListCommand();
            case TODO:
                return new TodoCommand(rest);
            case DEADLINE:
                return new DeadlineCommand(rest);
            case EVENT:
                return new EventCommand(rest);
            case DONE:
                return new DoneCommand(rest);
            case DELETE:
                return new DeleteCommand(rest);
            case FIND:
                return new FindCommand(rest);
            case BYE:
                return new ExitCommand();
            case HELP:
                return new HelpCommand();
            default:
                throw new InvalidCommandException();
            }
        } catch (IllegalArgumentException e) { //Thrown by value of
            throw new InvalidCommandException();
        }
    }
}
