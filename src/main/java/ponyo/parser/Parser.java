package ponyo.parser;

import ponyo.commands.AddCommand;
import ponyo.commands.Command;
import ponyo.commands.DeleteCommand;
import ponyo.commands.DoneCommand;
import ponyo.commands.ExitCommand;
import ponyo.commands.FindCommand;
import ponyo.commands.ListCommand;
import ponyo.common.Messages;
import ponyo.data.exceptions.PonyoException;

/**
 * Makes sense of the user commands
 */
public class Parser {

    /**
     * Parses the full input command to produce the type of command
     * to be executed.
     *
     * @param cmd the full input command
     * @return the type of command to be executed
     */
    public static Command parse(String cmd) {
        String[] commands = cmd.split(" ", 2);
        String prefix = commands[0];
        String commandArg = commands.length > 1 ? commands[1] : null;

        try {
            switch (prefix) {
            case "list":
            case "l":
                return new ListCommand();
            case "bye":
            case "exit":
                return new ExitCommand();
            case "done":
                assert commandArg != null;
                return new DoneCommand(Integer.parseInt(commandArg));
            case "todo":
            case "t":
            case "deadline":
            case "d":
            case "event":
            case "e":
                return new AddCommand(commands);
            case "find":
            case "f":
                assert commandArg != null;
                return new FindCommand(commandArg);
            case "delete":
            case "del":
                assert commandArg != null;
                return new DeleteCommand(Integer.parseInt(commandArg));
            default:
                throw new PonyoException(Messages.MESSAGE_INVALID);
            }
        } catch (PonyoException e) {
            throw new PonyoException(Messages.MESSAGE_IDK);
        }
    }
}
