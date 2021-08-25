package duke.utils;

import duke.commands.*;

public class Parser {
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("done")) {
            return new DoneCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("getat")) {
            return new GetAtCommand(input);
        } else if (input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline")) {
            return new AddCommand(input);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
