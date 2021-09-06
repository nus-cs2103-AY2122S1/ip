package duke;

import java.time.LocalDate;

// Duke Commands
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
// Duke Exceptions
import duke.exception.DukeException;
// Duke Tasks
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parses string inputs from user.
 */
public class Parser {
    private static final int COMMAND_EVENT_LENGTH = 6;
    private static final int COMMAND_DEADLINE_LENGTH = 9;
    private static final int COMMAND_TODO_LENGTH = 5;

    /**
     * Parses string inputs from user into a Command.
     *
     * @param input Input recieved from user.
     * @return Command corresponding to the input recieved.
     * @throws DukeException Invalid inputs recieved.
     */
    protected static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        int selectedTask;

        switch (inputArray[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            selectedTask = Integer.parseInt(inputArray[1]) - 1;
            return new DoneCommand(selectedTask);
        case "delete":
            selectedTask = Integer.parseInt(inputArray[1]) - 1;
            return new DeleteCommand(selectedTask);
        case "find":
            return new FindCommand(input.substring(5));
        case "event":
            return parseEvent(input);
        case "deadline":
            return parseDeadline(input);
        case "todo":
            return parseTodo(input);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseEvent(String input) {
        String[] params;
        params = input.split("/at");
        params[0] = params[0].substring(COMMAND_EVENT_LENGTH,
                params[0].length() - 1);
        params[1] = params[1].substring(1);
        return new AddCommand(new Event(params[0],
                LocalDate.parse(params[1])));
    }

    private static Command parseDeadline(String input) {
        String[] params;
        params = input.split("/by");
        params[0] = params[0].substring(COMMAND_DEADLINE_LENGTH,
                params[0].length() - 1);
        params[1] = params[1].substring(1);
        return new AddCommand(new Deadline(params[0],
                LocalDate.parse(params[1])));
    }

    private static Command parseTodo(String input) throws DukeException {
        try {
            String name = input.substring(COMMAND_TODO_LENGTH);
            if (name.equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(name));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
