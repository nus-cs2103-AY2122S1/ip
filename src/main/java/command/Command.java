package command;

import exception.DukeException;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import message.Message;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates a command after it is parsed from the user input.
 */
public abstract class Command {
    /**
     * Gets task number from a message.
     *
     * @param message Message from the user.
     * @return Integer task number.
     * @throws InvalidTaskNumberException If the message is not a number.
     */
    public static int getTaskNumberFromMessage(String message) throws InvalidTaskNumberException {
        try {
            String trimmedMessage = message.trim();
            return Integer.parseInt(trimmedMessage);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(message);
        }
    }

    /**
     * Validates that the command description is not empty.
     *
     * @param commandType Command type of the command.
     * @param description Description from the command excluding the command type.
     * @throws MissingCommandDescriptionException If description is empty.
     */
    public static void validateDescriptionNotEmpty(CommandTypeEnum commandType, String description)
            throws MissingCommandDescriptionException {
        if (description.isEmpty()) {
            throw new MissingCommandDescriptionException(commandType);
        }
    }

    /**
     * Executes a specific command.
     *
     * @param list `TaskList` containing all tasks.
     * @throws DukeException If there is any error.
     */
    public abstract Message execute(TaskList list) throws DukeException;
}
