package bot;

import command.Command;
import command.ListCommand;
import command.DoneCommand;
import command.DeleteCommand;
import command.TodoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.ExitCommand;

public class Parser {

    private Command command;

    public static Command parse(String input) throws DukeException {

        if (input.equals("list")) {

            return new ListCommand(input);

        } else if (input.startsWith("done ")) {

            return new DoneCommand(input);

        } else if (input.startsWith("delete ")) {

            return new DeleteCommand(input);

        } else if (input.startsWith("todo ")) {

            return new TodoCommand(input);

        } else if (input.startsWith("deadline ") && input.contains("/by ")) {

            return new DeadlineCommand(input);

        } else if (input.startsWith("event ") && input.contains("/at ")) {

            return new EventCommand(input);

        } else if (input.equals("bye")) {

            return new ExitCommand(input);

        } else {

            throw new DukeException("You've entered an unknown request... The bot doesn't know what that means!");

        }
    }
}
