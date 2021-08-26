package duke;

/**
 * Represents an error that only occurs for a Duke object.
 */
public class DukeException extends Exception{

    public DukeException(String message) {
        super(message);
    }

}
