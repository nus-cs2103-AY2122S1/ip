package duke.exceptions;

/**
 * Occurs when Duke doesn't recognise the input.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super ("Sowwy, thiws commandw iswn't supporwted! TwT");
    }
}