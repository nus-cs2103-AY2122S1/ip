package duke.fulfillment;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteTaskCommand;
import duke.command.FindTasksCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskDoneCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

/**
 * Utility class which provides methods for parsing and understanding user input.
 *
 * @author kevin9foong
 */
public class Parser {
    private Parser() {
    }

    /**
     * Parses user input and returns the appropriate <code>Command</code> which
     * corresponds to user input.
     *
     * @param userInput String which represents user's command for chat bot.
     * @return corresponding command as understood from user's input.
     * @throws DukeException thrown when user's input is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] splitUserInput = userInput.trim().split(" ", 2);
        CommandType userCommandType = CommandType.getCommand(splitUserInput[0].trim());
        String userInputBody = null;

        if (splitUserInput.length == 2) {
            userInputBody = splitUserInput[1];
        }
        if (userCommandType != null) {
            switch (userCommandType) {
            case LIST:
                return new ListTasksCommand(userInputBody);
            case TODO:
                return new AddTodoCommand(userInputBody);
            case DEADLINE:
                return new AddDeadlineCommand(userInputBody);
            case EVENT:
                return new AddEventCommand(userInputBody);
            case DONE:
                return new MarkTaskDoneCommand(userInputBody);
            case DELETE:
                return new DeleteTaskCommand(userInputBody);
            case FIND:
                return new FindTasksCommand(userInputBody);
            case BYE:
                return new ByeCommand(userInputBody);
            // default case in case unexpected no matches occurs.
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
