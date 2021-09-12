package bot;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.TagCommand;
import command.TodoCommand;

/**
 * A class that handles the parsing of various inputs given to Duke by the user.
 */
public class Parser {

    /**
     * Returns a Command object according to the input given by the user.
     * Parses the user input using string matching.
     *
     * @param input The input given by the user.
     * @return A Command object that corresponds to the input given.
     * @throws DukeException if the input could not be parsed.
     */
    public static Command parse(String input) throws DukeException {

        if (input.equals("list")) {

            return new ListCommand(input);

        } else if (input.startsWith("done ")) {

            return new DoneCommand(input);

        } else if (input.startsWith("delete ")) {

            return new DeleteCommand(input);

        } else if (input.startsWith("tag ")) {

            return new TagCommand(input);

        } else if (input.startsWith("todo ")) {

            return new TodoCommand(input);

        } else if (input.startsWith("deadline ")) {

            return new DeadlineCommand(input);

        } else if (input.startsWith("event ")) {

            return new EventCommand(input);

        } else if (input.startsWith("find ")) {

            return new FindCommand(input);

        } else if (input.equals("bye")) {

            return new ExitCommand(input);

        } else {

            throw new DukeException("You've entered an unknown request... Duke doesn't know what that means!");

        }
    }
}
