package exception;

import type.CommandTypeEnum;

/**
 * Encapsulates an exception when a user inputs a command with an empty description,
 * but the command requires a description.
 */
public class MissingCommandDescriptionException extends DukeException {
    /**
     * Instantiates an exception when a user inputs a command with an empty description.
     *
     * @param commandType Command type.
     */
    public MissingCommandDescriptionException(CommandTypeEnum commandType) {
        super(String.format(
                "The description of a %s command cannot be empty. Please enter the command in this format: %s",
                commandType.toString(),
                commandType.getFormat()
        ));
    }
}
