package duke;
/**
 * Class that extends exception.
 * Allows for customised message.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
