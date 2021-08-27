package duke.exception;

/**
 * This class encapsulates exception due unexpected user input.
 *
 * @author Teo Sin Yee
 */
public class DukeNoSuchCommandException extends DukeException {
    public DukeNoSuchCommandException() {
        super("I don't quite understand you. :-(");
    }
}