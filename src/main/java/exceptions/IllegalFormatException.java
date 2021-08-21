package exceptions;

/**
 * Represents an exception for the chatbot.
 * Thrown when user did not use the correct format when using the chat bot.
 * Example: deadline without /by, event without /at, etc
 */

public class IllegalFormatException extends DukeException {

    public IllegalFormatException() {
        super("Wrong Format used! Please you the correct format!");
    }
}

