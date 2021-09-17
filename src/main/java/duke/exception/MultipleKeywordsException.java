package duke.exception;

/**
 * This class encapsulates exception due to multiple keywords in user input.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class MultipleKeywordsException extends DukeException {
    public MultipleKeywordsException() {
        super("MultipleKeywordsException: Unable to search for multiple keywords");
    }
}
