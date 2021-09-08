package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.exception.Messages;

/**
 * Parses user command into respective commands.
 */
public class Parser {
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
            return new AddCommand(Cutter.cut(cmd, "add"));
        } else if (cmd.contains("list")) {
            return new ListCommand("");
        } else if (cmd.contains("done")) {
            return new DoneCommand(Cutter.cut(cmd, "done"));
        } else if (cmd.contains("delete")) {
            return new DeleteCommand(Cutter.cut(cmd, "delete"));
        } else if (cmd.contains("bye")) {
            return new ExitCommand("");
        } else if (cmd.contains("find")) {
            return new FindCommand(Cutter.cut(cmd, "find"));
        } else if (cmd.contains("sort")) {
            return new SortCommand(Cutter.cut(cmd, "sort"));
        } else {
            throw new DukeException(Messages.KNOWN.toString());
        }
    }
}
