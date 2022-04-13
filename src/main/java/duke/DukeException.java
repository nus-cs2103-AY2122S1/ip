package duke;

/**
 * Encapsulates the exceptions that can be thrown by Duke as it runs.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
