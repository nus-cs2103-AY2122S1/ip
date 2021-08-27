package duke.exception;

/**
 * This class encapsulates exception due to unexpected data in storage when loading tasks.
 *
 * @author Teo Sin Yee
 */
public class DukeUnexpectedCharacterException extends DukeException {
    public DukeUnexpectedCharacterException(String symbol) {
        super(String.format("Unexpected symbol: %s ", symbol));
    }
}