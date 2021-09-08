package duke.exceptions;

/**
 * Class that handles invalid input exceptions.
 */
public class InvalidInputException extends DukeException {
    /**
     * Occurs when Duke doesn't recognise the input.
     */
    public InvalidInputException() {
        super("Sowwy, thiws commandw iswn't supporwted! TwT");
    }
}