package duke.exception;

/**
 * This class encapsulates exception due to negative index input by user.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class DukeNegativeIndexException extends DukeException {
    public DukeNegativeIndexException() {
        super("DukeNegativeIndexException: You can't have negative tasks!");
    }
}
