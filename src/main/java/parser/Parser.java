package parser;

import command.*;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentCommandTypeException;
import type.DukeCommandTypeEnum;

public class Parser {
    private final static String EXIT_COMMAND = "bye";

    public boolean detectExitCommand(String inputMessage) {
        return inputMessage.trim().equals(EXIT_COMMAND);
    }

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
