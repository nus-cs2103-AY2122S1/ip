package duke.exception;

/**
 * This class encapsulates exception due unexpected user input.
 *
 * @author Teo Sin Yee
 */
public class DukeNoSuchCommandException extends DukeException {
    /**
     * Constructor for DukeNoSuchCommandException.
     */
    public DukeNoSuchCommandException() {
        super("Error: I don't quite understand you. :-(");
    }
}