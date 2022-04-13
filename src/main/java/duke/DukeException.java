package duke;

/**
 * Represents an exception that occurs when running the Duke program. Takes in an error message.
 */
public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}
