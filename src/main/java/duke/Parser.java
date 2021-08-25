package duke;

import duke.command.*;

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
        } else {
            // Invalid command
            throw new DukeException("Sorry, I didn't understand what you meant by that");
        }
    }
}
