package exception;

import type.CommandTypeEnum;

/**
 * Encapsulates an exception when a command is incorrectly formatted.
 */
public class InvalidCommandFormatException extends DukeException {
    /**
     * Instantiates an exception when a command is incorrectly formatted.
     *
     * @param commandType Command type.
     */
    public InvalidCommandFormatException(CommandTypeEnum commandType) {
        super(String.format(
            "A valid %s command should have the form of %s",
            commandType.toString(),
            commandType.getFormat()
        ));
    }
}
