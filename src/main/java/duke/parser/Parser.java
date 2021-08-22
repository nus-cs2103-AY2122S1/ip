package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.exception.DukeException;

/**
 * Parses user command into respective commands.
 */
public class Parser {
    /**
     * Returns command body of task request.
     * Substrings input line after start word till end of string.
     *
     * @param input user command line.
     * @param start substring after target word.
     */
    private static String cut(String input, String start){
        return input.substring(input.indexOf(start) + start.length() + 1);
    }

    /**
     * Returns a command, respective to user command/request.
     * If command is add, returns AddCommand.
     * If command is delete, returns DeleteCommand.
     * If command is exit, returns ExitCommand.
     *
     * @param cmd user command line.
     * @throws DukeException If invalid command.
     */
    public static Command parse(String cmd) throws DukeException {
        if (cmd.contains("add")) {
            return new AddCommand(cut(cmd, "add"));
        } else if (cmd.contains("delete")) {
            return new DeleteCommand(cut(cmd, "delete"));
        } else if (cmd.contains("exit")) {
            return new ExitCommand("");
        } else {
            throw new DukeException("Invalid command");
        }
    }
}
