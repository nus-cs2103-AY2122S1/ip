package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.Messages;

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
     * @return task details.
     */
    private static String cut(String input, String start) {
        return input.substring(input.indexOf(start) + start.length() + 1);
    }

    /**
     * Returns a command, respective to user command/request.
     * If command is add, returns AddCommand.
     * If command is delete, returns DeleteCommand.
     * If command is exit, returns ExitCommand.
     *
     * @param cmd user command line.
     * @return task command.
     * @throws DukeException If invalid command.
     */
    public static Command parse(String cmd) throws DukeException {
        if (cmd.contains("add")) {
            return new AddCommand(cut(cmd, "add"));
        } else if (cmd.contains("list")) {
            return new ListCommand("");
        } else if (cmd.contains("done")) {
            return new DoneCommand(cut(cmd, "done"));
        } else if (cmd.contains("delete")) {
            return new DeleteCommand(cut(cmd, "delete"));
        } else if (cmd.contains("bye")) {
            return new ExitCommand("");
        } else if (cmd.contains("find")) {
            return new FindCommand(cut(cmd, "find"));
        } else {
            throw new DukeException(Messages.KNOWN.toString());
        }
    }
}
