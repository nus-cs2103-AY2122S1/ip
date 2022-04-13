package duke.exception;

/**
 * Exception of Duke that occurs when message of task is empty.
 */
public class MessageEmptyException extends DukeException {
    public MessageEmptyException() {
        super("You forgot to enter a message after the command!");
    }
}
