package fulfillment;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.ByeCommand;
import command.Command;
import command.CommandType;
import command.DeleteTaskCommand;
import command.ListTasksCommand;
import command.MarkTaskDoneCommand;
import exceptions.DukeException;
import exceptions.InvalidCommandException;

public class Parser {
    private Parser() {
    }

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
