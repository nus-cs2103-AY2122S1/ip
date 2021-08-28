package main.java.bot;

import main.java.command.Command;
import main.java.command.ListCommand;
import main.java.command.DoneCommand;
import main.java.command.DeleteCommand;
import main.java.command.TodoCommand;
import main.java.command.DeadlineCommand;
import main.java.command.EventCommand;
import main.java.command.ExitCommand;

/**
 * A class that handles the parsing of various inputs given to Duke by the user.
 */
public class Parser {

    private Command command;

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

        } else if (input.startsWith("todo ")) {

            return new TodoCommand(input);

        } else if (input.startsWith("deadline ") && input.contains("/by ")) {

            return new DeadlineCommand(input);

        } else if (input.startsWith("event ") && input.contains("/at ")) {

            return new EventCommand(input);

        } else if (input.equals("bye")) {

            return new ExitCommand(input);

        } else {

            throw new DukeException("You've entered an unknown request... The main.java.bot doesn't know what that means!");

        }
    }
}
