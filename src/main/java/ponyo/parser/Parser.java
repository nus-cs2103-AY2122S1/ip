package ponyo.parser;

import ponyo.commands.AddCommand;
import ponyo.commands.Command;
import ponyo.commands.DeleteCommand;
import ponyo.commands.DoneCommand;
import ponyo.commands.ExitCommand;
import ponyo.commands.FindCommand;
import ponyo.commands.ListCommand;
import ponyo.data.exceptions.PonyoException;

/**
 * Makes sense of the user commands
 */
public class Parser {
    private static final String MESSAGE_IDK = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Parses the full input command to produce the type of command
     * to be executed.
     *
     * @param cmd the full input command
     * @return the type of command to be executed
     */
    public static Command parse(String cmd) {
        String[] cmds = cmd.split(" ", 2);

        try {
            switch (cmds[0]) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "done":
                return new DoneCommand(Integer.parseInt(cmds[1]));
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(cmds);
            case "find":
                return new FindCommand(cmds[1]);
            case "delete":
                return new DeleteCommand(Integer.parseInt(cmds[1]));
            default:
                throw new PonyoException("Invalid command given!");
            }
        } catch (PonyoException e) {
            throw new PonyoException(MESSAGE_IDK);
        }
    }
}
