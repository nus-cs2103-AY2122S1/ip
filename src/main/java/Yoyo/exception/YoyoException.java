package Yoyo.exception;

import java.io.IOException;

/**
 * Exception class for duke.Yoyo's commands.
 */
public class YoyoException extends IOException {

    public YoyoException(String message) {
        super(message);
    }

    /**
     * Exception class for command with invalid format .
     */
    public static class YoyoInvalidFormatException extends YoyoException {
        public YoyoInvalidFormatException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for incomplete command .
     */
    public static class YoyoIncompleteCommandException extends YoyoException {
        public YoyoIncompleteCommandException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for invalid command.
     */
    public static class YoyoCommandNotFoundException extends YoyoException {
        public YoyoCommandNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for invalid task index.
     */
    public static class YoyoTaskIndexException extends YoyoException {
        public YoyoTaskIndexException(String message) {
            super(message);
        }
    }

    /**
     * Exception class for empty command.
     */
    public static class YoyoEmptyCommandException extends YoyoException {
        public YoyoEmptyCommandException(String message) {
            super(message);
        }
    }
}
