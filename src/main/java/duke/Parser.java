package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

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
    public static Command parse(Input input) throws DukeException {
        String tag = input.checkType();
        if (tag.equals("bye")) {
            return new ExitCommand();
        } else if (tag.equals("list")) {
            return new ListCommand();
        } else if (tag.equals("done")) {
            return new DoneCommand(input);
        } else if (tag.equals("delete")) {
            return new DeleteCommand(input);
        } else if (tag.equals("find")) {
            return new FindCommand(input);
        } else if (tag.equals("todo")) {
            return new TodoCommand(input);
        } else if (tag.equals("deadline")) {
            return new DeadlineCommand(input);
        } else if (tag.equals("event")) {
            return new EventCommand(input);
        } else {
            return new ErrorCommand();
        }
    }
}
