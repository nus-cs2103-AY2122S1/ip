package duke.main;

import java.util.Arrays;
import java.util.List;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;


/**
 * Represents a class that handles reading all user input
 */
public class Parser {
    private enum ValidCommand {
        DONE,
        DELETE,
        LIST,
        DEADLINE,
        TODO,
        EVENT,
        FIND,
        INVALID,
        BYE
    }

    private static boolean isLowerCase(String input) {
        return input.equals(input.toLowerCase());
    }

    /**
     * Creates Command object from user input in String format
     *
     * @param userInput user input in string format
     * @return Command object to be executed
     * @throws DukeException If user enters empty description/time
     */
    public static Command parse(String userInput) throws DukeException {
        List<String> userInputList = Arrays.asList(userInput.split(" "));
        String userCommandString = userInputList.get(0);

        ValidCommand command;

        try {
            if (Parser.isLowerCase(userCommandString)) {
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
        } else if (command == ValidCommand.FIND) {
            return new FindCommand(userInput);
        }
        return null;
    }
}
