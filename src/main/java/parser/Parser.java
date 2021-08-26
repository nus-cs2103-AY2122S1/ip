package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;

import exception.DukeException;

import task.Deadline;
import task.Event;
import task.Todo;

public class Parser {
    // TODO: Simplify program and exceptions
    public static Command parse(String input) throws DukeException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("done ")) {
            int index = Integer.parseInt(input.substring(5));
            return new MarkCommand(index);
        } else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.substring(7));
            return new DeleteCommand(index);
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5);
            return new AddCommand(new Todo(description));
        } else if (input.startsWith("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new DukeException("Invalid format for `deadline` command. '/by' keyword is needed");
            }
            String[] params = input.substring(9).split(" /by ");
            String description = params[0];
            String by = params[1];
            return new AddCommand(new Deadline(description, by));
        } else if (input.startsWith("event ")) {
            if (!input.contains(" /at ")) {
                throw new DukeException("Invalid format for `event` command. '/at' keyword is needed");
            }
            String[] params = input.substring(6).split(" /at ");
            String description = params[0];
            String at = params[1];

            return new AddCommand(new Event(description, at));
        } else {
            throw new DukeException("Invalid command: " + input);
        }
    }
}
