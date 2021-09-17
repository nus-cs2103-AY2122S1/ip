package duke.exception;

/**
 * This class encapsulates exception due to user inputted index being out of bounds.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int size) {
        super(String.format("InvalidIndexException: You only have %d tasks!", size));
    }
}
