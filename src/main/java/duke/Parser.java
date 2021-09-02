package duke;

import duke.command.*;

/**
 * Class for parsing the user commands.
 */
public class Parser {

    /**
     * Returns commands according to the uder input.
     *
     * @param input The user input.
     * @return The respective commands.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return new DoneCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else {
            return new ErrorCommand();
        }
    }
}
