package duke.exceptions;

/**
 * Represents an exception for the chat bot.
 * Thrown when an unknown tag is entered by the user.
 */

public class UnknownTagException extends DukeException {

    public UnknownTagException() {
        super("No such tag! Please input the correct tag!");
    }
}
