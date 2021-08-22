package duke.exception;

/**
 * This class encapsulates exception due to missing keyword in user input.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class MissingKeywordException extends DukeException {
    public MissingKeywordException() {
        super("MissingQueryException: Please enter a keyword to let me know what you are searching for.");
    }
}
