package data;

/**
 * Class that contains errors that are specific to Duke.
 */
public class DukeException extends RuntimeException{
    public DukeException(String message) {
        super(message);
    }
}
