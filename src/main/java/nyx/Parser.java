package nyx;

import nyx.command.Command;
import nyx.command.DeadlineCommand;
import nyx.command.DeleteCommand;
import nyx.command.DoneCommand;
import nyx.command.EventCommand;
import nyx.command.FindCommand;
import nyx.command.ListCommand;
import nyx.command.ToDoCommand;
import nyx.command.UpdateCommand;

/**
 * Deals with making sense of the user input.
 */

public class Parser {
    /**
     * Interprets the user input and executes the command specified in the input using TaskList and Storage objects.
     *
     * @param input String representation of the user input.
     * @return Command object to be executed
     * @throws NyxException If the input keyword is wrong or if the input lacks information
     */
    public static Command parse(String input) throws NyxException {
        String[] splitInput = input.split(" ", 2);
        String keyword = splitInput[0].toLowerCase();
        String information = "";
        if (splitInput.length > 1) {
            information = splitInput[1].strip();
        }

        switch (keyword) {
        case "list":
            return new ListCommand();
        case "done":
            if (information.isEmpty()) {
                DoneCommand.throwEmptyException();
            }
            return new DoneCommand(information);
        case "todo":
            if (information.isEmpty()) {
                ToDoCommand.throwEmptyException();
            }
            return new ToDoCommand(information);
        case "deadline":
            if (information.isEmpty()) {
                DeadlineCommand.throwEmptyException();
            }
            return new DeadlineCommand(information);
        case "event":
            if (information.isEmpty()) {
                EventCommand.throwEmptyException();
            }
            return new EventCommand(information);
        case "delete":
            if (information.isEmpty()) {
                DeleteCommand.throwEmptyException();
            }
            return new DeleteCommand(information);
        case "find":
            if (information.isEmpty()) {
                FindCommand.throwEmptyException();
            }
            return new FindCommand(information);
        case "update":
            if (information.isEmpty()) {
                UpdateCommand.throwEmptyException();
            }
            return new UpdateCommand(information);
        default:
            throw new NyxException("I dont understand this command... Please try again.");
        }
    }
}
