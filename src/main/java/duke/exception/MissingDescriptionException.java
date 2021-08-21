package duke.exception;

/**
 * This class encapsulates exception due to missing accompanying description in user input.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */

public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException() {
        super("MissingDescriptionException: Command issued has to be followed by a description.");
    }
}
