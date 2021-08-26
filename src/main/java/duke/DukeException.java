package duke;

/**
 * DukeException class handles exceptions for Duke.
 */
public class DukeException extends Exception{

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Oh no! " + this.getMessage();
    }
}
