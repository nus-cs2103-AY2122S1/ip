package duke.exceptions;

/**
 * Class that handles missing input exceptions.
 */
public class MissingInputException extends DukeException {
    /**
     * Occurs when Duke recognises keyword, but there is no input after it.
     * @param keyword Keyword input recognised by Duke.
     */
    public MissingInputException(String keyword) {
        super("Oopsie uwu! Youw cawn't use " + keyword + " withoutw a descwiption forw it!");
    }
}
