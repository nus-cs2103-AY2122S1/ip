package duke.exceptions;

/**
 * Represents an exception for the chat bot.
 * Thrown when user did not use the correct format when using the chat bot.
 * Example: deadline without /by, event without /at, etc
 */

public class IllegalFormatException extends DukeException {

    public IllegalFormatException(String msg) {
        super("Wrong format! " + msg);
    }
}

