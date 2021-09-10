package exception;

import type.CommandTypeEnum;

/**
 * Encapsulates an exception when a task is incorrectly formatted.
 */
public class InvalidTaskFormatException extends DukeException {
    /**
     * Instantiates an exception when a task is incorrectly formatted.
     *
     * @param commandType Task command type.
     */
    public InvalidTaskFormatException(CommandTypeEnum commandType) {
        super(String.format(
            "A valid %s task should have the form of %s",
            commandType.toString(),
            commandType.getFormat()
        ));
    }
}
