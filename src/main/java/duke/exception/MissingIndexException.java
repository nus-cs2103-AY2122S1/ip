package duke.exception;

/**
 * This class encapsulates exception due to missing index in user input.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class MissingIndexException extends DukeException {
    public MissingIndexException() {
        super("MissingIndexException: Please enter an index to let me know which task you are referring to.");
    }
}
