package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/** A parser class that parses user's commands. */
public class Parser {
    /**
     * Parses the input into its corresponding commands.
     *
     * @param input The string command.
     * @return The corresponding Command object.
     * @throws DukeException If the command is invalid.
     */
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
