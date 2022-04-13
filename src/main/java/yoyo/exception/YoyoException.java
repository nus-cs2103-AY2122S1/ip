package yoyo.exception;

import java.io.IOException;

/**
 * Exception class containing all of Yoyo-related exceptions.
 */
public class YoyoException extends IOException {

    /**
     * Constructor for YoyoException class.
     *
     * @param message Exception message.
     */
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

    /**
     * Exception class for storage errors.
     */
    public static class YoyoStorageException extends YoyoException {
        public YoyoStorageException(String message) {
            super(message);
        }
    }
}
