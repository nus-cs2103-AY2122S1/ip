package duke.util;

/**
 * Exception specific to Duke.
 */
public class DukeException extends RuntimeException {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("Nonsense, %s", this.getMessage());
    }
}
