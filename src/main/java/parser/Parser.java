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
import exception.UnhandledCommandException;
import type.CommandTypeEnum;

/**
 * Encapsulates a `Parser` that parses string inputs to commands or throws exceptions if they are invalid.
 */
public class Parser {
    /**
     * Detects if an input is the exit command.
     *
     * @param inputMessage Input string taken in by System.in.
     * @return True if it the input is the exit command, false otherwise.
     */
    public boolean detectExitCommand(String inputMessage) {
        return inputMessage.trim().equals(CommandTypeEnum.BYE.toString());
    }

    /**
     * Creates a command from the input string.
     *
     * @param message Input string taken in by System.in.
     * @return `Command`.
     * @throws InvalidTaskNumberException If the `Command` has an invalid task number.
     * @throws NonExistentCommandTypeException If the `Command` has a command type that is not recognised.
     * @throws MissingCommandDescriptionException If the `Command` has a missing description.
     */
    public Command createCommand(String message) throws
            InvalidTaskNumberException,
            NonExistentCommandTypeException,
            MissingCommandDescriptionException,
            UnhandledCommandException {
        CommandTypeEnum commandType = getCommandType(message);
        String messageWithoutCommand = removeCommandTypePrefix(message, commandType);
        String trimmedMessageWithoutCommand = messageWithoutCommand.trim();

        switch (commandType) {
        case LIST:
            return ListCommand.createCommand();
        case DONE:
            return DoneCommand.createCommand(trimmedMessageWithoutCommand);
        case DELETE:
            return DeleteCommand.createCommand(trimmedMessageWithoutCommand);
        case FIND:
            return FindCommand.createCommand(trimmedMessageWithoutCommand);
        case TODO:
            return AddCommand.createCommand(trimmedMessageWithoutCommand, commandType);
        case DEADLINE:
            return AddCommand.createCommand(trimmedMessageWithoutCommand, commandType);
        case EVENT:
            return AddCommand.createCommand(trimmedMessageWithoutCommand, commandType);
        default:
            throw new UnhandledCommandException(commandType);
        }
    }

    private CommandTypeEnum getCommandType(String message) throws NonExistentCommandTypeException {
        String trimmedMessage = message.trim();
        String[] messagesSplitUsingSpace = trimmedMessage.split(" ");
        String commandWord = messagesSplitUsingSpace[0];

        for (CommandTypeEnum commandType : CommandTypeEnum.values()) {
            if (commandWord.equals(commandType.toString())) {
                return commandType;
            }
        }

        throw new NonExistentCommandTypeException(message);
    }

    private String removeCommandTypePrefix(String message, CommandTypeEnum commandType) {
        String commandTypeString = commandType.toString();
        return message.trim().substring(commandTypeString.length());
    }
}
