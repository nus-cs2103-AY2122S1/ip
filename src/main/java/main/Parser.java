import commands.*;
import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import exceptions.InvalidCommandException;
import utils.Util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private enum ValidCommand {
        DONE,
        DELETE,
        LIST,
        DEADLINE,
        TODO,
        EVENT,
        INVALID,
        BYE
    }

    public static Command parse(String userInput) throws EmptyDescriptionException, EmptyTimeException, InvalidCommandException {
        List<String> userInputList = Arrays.asList(userInput.split(" "));
        String userCommandString = userInputList.get(0);

        ValidCommand command;

        try {
            if (Util.isLowerCase(userCommandString)) {
                command = ValidCommand.valueOf(userCommandString.toUpperCase());
            } else {
                command = ValidCommand.INVALID;
            }
        } catch (IllegalArgumentException e) {
            command = ValidCommand.INVALID;
        }

        if (command == ValidCommand.DONE) {
            return new DoneCommand(userInput);
        } else if (command == ValidCommand.DELETE) {
            return new DeleteCommand(userInput);
        } else if (command == ValidCommand.LIST) {
            return new ListCommand(userInput);
        } else if (command == ValidCommand.DEADLINE) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.TODO) {
            return new TodoCommand(userInput);
        } else if (command == ValidCommand.EVENT) {
            return new EventCommand(userInput);
        } else if (command == ValidCommand.INVALID) {
            throw new InvalidCommandException();
        } else if (command == ValidCommand.BYE) {
            return new ByeCommand();
        }
        return null;
    }
}
