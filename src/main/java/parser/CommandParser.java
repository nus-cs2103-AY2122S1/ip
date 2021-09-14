package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import exception.InvalidNumOfStringPartsException;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentCommandTypeException;
import exception.UnhandledCommandException;
import type.CommandTypeEnum;

/**
 * Encapsulates a parser that parses string inputs to commands or throws exceptions if they are invalid.
 */
public class CommandParser {
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
     * @throws InvalidTaskNumberException If the command has an invalid task number.
     * @throws NonExistentCommandTypeException If the command has a command type that is not recognised.
     * @throws MissingCommandDescriptionException If the command has a missing description.
     * @throws UnhandledCommandException If a command is not handled.
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
        case HELP:
            return HelpCommand.createCommand();
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

    /**
     * Splits a string into an array of elements by a given splitter.
     *
     * @param input Input to split.
     * @param splitter Splitter to split the string by.
     * @return Array of strings.
     */
    public static String[] splitStringBySplitter(String input, String splitter) {
        String[] splitParts = input.split(splitter);

        // Trim split parts to remove whitespace before and after
        for (int i = 0; i < splitParts.length; i++) {
            splitParts[i] = splitParts[i].trim();
        }

        return splitParts;
    }

    /**
     * Validates that an array of strings has the correct number of parts.
     *
     * @param expectedNumOfParts Expected number of parts.
     * @param splitParts Array of strings.
     * @throws InvalidNumOfStringPartsException If the number of parts is wrong.
     */
    public static void validateCorrectNumOfParts(
            int expectedNumOfParts,
            String[] splitParts) throws InvalidNumOfStringPartsException {
        if (splitParts.length != expectedNumOfParts) {
            throw new InvalidNumOfStringPartsException(expectedNumOfParts, splitParts);
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
