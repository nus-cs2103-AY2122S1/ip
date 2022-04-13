package exception;

import type.CommandTypeEnum;

/**
 * Encapsulates an exception when the command is valid but not handled.
 */
public class UnhandledCommandException extends DukeException {
    /**
     * Instantiates an exception when the command is valid but not handled.
     * @param commandType Command Type.
     */
    public UnhandledCommandException(CommandTypeEnum commandType) {
        super(String.format("There was an error in handling the command '%s'", commandType.toString()));
    }
}
