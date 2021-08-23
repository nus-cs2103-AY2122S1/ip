package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.exception.Messages;

public class Parser {
    private static String cut(String input, String start){
        return input.substring(input.indexOf(start) + start.length() + 1);
    }

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
        }  else {
            throw new DukeException(Messages.KNOWN.toString());
        }
    }
}
