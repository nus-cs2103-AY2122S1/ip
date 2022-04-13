package duke.exceptions;

/**
 * Class that handles missing number exceptions.
 */
public class MissingNoException extends DukeException {
    /**
     * Occurs when Duke recognises keyword, but input (number) after it is invalid.
     * @param keyword Keyword input recognised by Duke.
     */
    public MissingNoException(String keyword) {
        super("Thwere's no suchw taskw! Pwease enterw a *validw* numbewr after '"+ keyword + "'!");
    }
}