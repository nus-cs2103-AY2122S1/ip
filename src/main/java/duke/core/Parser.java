package duke.core;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;

/**
 * Encapsulates the Parser class which reads the user inputs and identify what the user
 * wants Duke to do.
 *
 * @author Clifford
 */
public class Parser {
    /**
     * Identifies the command the user wishes to invoke given a user input, otherwise throws
     * DukeException if user input is invalid.
     *
     * @param input the user input
     * @return the command the user wishes to invoke
     * @throws DukeException if there is an absence of user input or
     *     a valid command with missing arguments is entered
     */
    public static Command identifyCommand(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("Go on, I'm all ears!");
        }
        String[] fullCommand = input.trim().split(" ", 2);
        final String command = fullCommand[0].trim();
        final String description = fullCommand.length > 1 ? fullCommand[1].trim() : "";
        switch(command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "sort":
            return new SortCommand();
        case "done":
            return new DoneCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "todo":
            return new AddTodoCommand(description);
        case "deadline":
            return new AddDeadlineCommand(description);
        case "event":
            return new AddEventCommand(description);
        case "find":
            return new FindCommand(description);
        default:
            throw new DukeException("I don't understand what you mean :(");
        }
    }
}
