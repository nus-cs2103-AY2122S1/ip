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
        try {
            if (command.equals("bye")) {
                return new ExitCommand();
            }
            if (command.trim().equals("list")) {
                return new ListCommand();
            }
            if (command.equals("done")) {
                return new DoneCommand(fullCommand[1].trim());
            }
            if (command.equals("delete")) {
                return new DeleteCommand(fullCommand[1].trim());
            }
            if (command.equals("todo")) {
                return new AddTodoCommand(fullCommand[1].trim());
            }
            if (command.equals("deadline")) {
                return new AddDeadlineCommand(fullCommand[1].trim());
            }
            if (command.equals("event")) {
                return new AddEventCommand(fullCommand[1].trim());
            }
            if (command.equals("find")) {
                return new FindCommand(fullCommand[1].trim());
            }
            throw new DukeException("Sorry, I don't understand what you are saying!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Don't be shy! I need more information to carry out " + command + " :(");
        }
    }
}
