package exception;

import type.CommandTypeEnum;

/**
 * Encapsulates an exception when a command is incorrectly formatted.
 */
public class InvalidCommandFormatException extends DukeException {
    /**
     * Instantiates an exception when a command is incorrectly formatted.
     *
     * @param commandType Command Type of the input.
     */
    public InvalidCommandFormatException(CommandTypeEnum commandType) {
        super(
                String.format(
                        "A %s command should be in this format: %s",
                        commandType.toString(),
                        commandType.getFormat()
                )
        );
    }
}
