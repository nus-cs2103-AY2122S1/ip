package duke.util;

import duke.command.*;
import duke.exceptions.UnknownException;

/**
 * Parser class to handle user commands.
 */
public class Parser {
    /**
     * Returns command objects according to user command.
     *
     * @param command User's command input.
     * @return Respective command object.
     * @throws UnknownException In case of errors.
     */
    public static Command parse(String command) throws UnknownException {
        if (command.startsWith("todo")) {
            return new AddCommand(command, "todo");
        } else if (command.startsWith("deadline")) {
            return new AddCommand(command, "deadline");
        } else if (command.startsWith("event")) {
            return new AddCommand(command, "event");
        } else if (command.startsWith("done")) {
            return new DoneCommand(command);
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            return new FindCommand(command);
        } else {
            throw new UnknownException();
        }
    }
}
