package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.FindCommand;
import command.ListCommand;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentCommandTypeException;
import type.DukeCommandTypeEnum;

/**
 * Encapsulates a `Parser` that parses string inputs to commands or throws exceptions if they are invalid.
 */
public class Parser {
    private static final String EXIT_COMMAND = "bye";

    /**
     * Detects if an input is the exit command.
     *
     * @param inputMessage Input string taken in by System.in.
     * @return True if it the input is the exit command, false otherwise.
     */
    public boolean detectExitCommand(String inputMessage) {
        return inputMessage.trim().equals(EXIT_COMMAND);
    }

    /**
     * Creates a command from the input string.
     *
     * @param inputMessage Input string taken in by System.in.
     * @return `Command`.
     * @throws InvalidTaskNumberException If the `Command` has an invalid task number.
     * @throws NonExistentCommandTypeException If the `Command` has a command type that is not recognised.
     * @throws MissingCommandDescriptionException If the `Command` has a missing description.
     */
    public Command makeCommand(String inputMessage)
            throws InvalidTaskNumberException, NonExistentCommandTypeException, MissingCommandDescriptionException {
        DukeCommandTypeEnum commandType = getCommandType(inputMessage);
        String messageWithoutCommand = removeCommandPrefix(inputMessage, commandType.toString());
        String trimmedMessage = messageWithoutCommand.trim();

        switch (commandType) {
        case LIST:
            return ListCommand.createCommand();
        case DONE:
            return DoneCommand.createCommand(trimmedMessage);
        case DELETE:
            return DeleteCommand.createCommand(trimmedMessage);
        case FIND:
            return FindCommand.createCommand(trimmedMessage);
        default:
            return AddCommand.createCommand(trimmedMessage, commandType);
        }
    }

    private DukeCommandTypeEnum getCommandType(String message) throws NonExistentCommandTypeException {
        String trimmedMessage = message.trim();
        String[] messagesSplitUsingSpace = trimmedMessage.split(" ");
        String commandWord = messagesSplitUsingSpace[0];

        for (DukeCommandTypeEnum commandType : DukeCommandTypeEnum.values()) {
            int commandTypeLength = commandType.toString().length();
            if (commandWord.length() >= commandTypeLength && commandWord.equals(commandType.toString())) {
                return commandType;
            }
        }

        throw new NonExistentCommandTypeException(message);
    }

    private String removeCommandPrefix(String message, String command) {
        return message.trim().substring(command.length());
    }
}
