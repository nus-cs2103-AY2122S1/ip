package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.exception.DukeException;

public class Parser {
    private static String cut(String input, String start){
        return input.substring(input.indexOf(start) + start.length() + 1);
    }

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
