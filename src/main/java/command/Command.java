package command;

import tasklist.TaskList;
import ui.message.Message;
import exception.DukeException;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import type.DukeCommandTypeEnum;

public abstract class Command {
    public static int getTaskNumberFromMessage(String message) throws InvalidTaskNumberException {
        try {
            String trimmedMessage = message.trim();
            return Integer.parseInt(trimmedMessage);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(message);
        }
    }

    public static void validateDescriptionNotEmpty(DukeCommandTypeEnum actionType, String description)
            throws MissingCommandDescriptionException {
        if (description.isEmpty()) {
            throw new MissingCommandDescriptionException(actionType.toString());
        }
    }

    public abstract void execute(TaskList list) throws DukeException;

    public abstract Message getOutputMessage();
}
