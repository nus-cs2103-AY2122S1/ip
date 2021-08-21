package duke.exception;

/**
 * This class encapsulates exception due to file IO issues.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class DukeIOException extends DukeException {
    public DukeIOException() {
        super("DukeIOException: Unable to write to data file. Your data will not be saved!");
    }
}
