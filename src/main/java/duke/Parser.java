package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkAsDoneCommand;
import duke.command.SortCommand;

/**
 * Parses commands for duke.Duke.
 */
public class Parser {
    /**
     * Parses an input command and returns a corresponding duke.commands.Command.
     * @param input single-line user input
     * @return duke.commands.Command which performs the corresponding action.
     */
    static Command parse(String input) {

        assert input != null;

        if (input.equals("bye")) {
            return new ByeCommand(input);
        } else if (input.equals("list")) {
            // Check if user is requesting to print list.
            return new ListCommand(input);
        } else if (input.startsWith("done")) {
            return new MarkAsDoneCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("sort")) {
            return new SortCommand(input);
        } else {
            // Invalid command
            throw new DukeException("Sorry, I didn't understand what you meant by that");
        }
    }
}
