package duke.exception;

/**
 * This class encapsulates exception due to wrong format of index in user input.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class IndexFormatException extends DukeException {
    public IndexFormatException() {
        super("IndexFormatException: Index entered should only contain positive numerals.");
    }
}
