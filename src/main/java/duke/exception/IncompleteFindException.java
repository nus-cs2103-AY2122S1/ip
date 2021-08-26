package duke.exception;

import static duke.Ui.LINE;

/**
 * Representation for incomplete keyword for FindCommand exception.
 */
public class IncompleteFindException extends DukeException{
    private static String message = "OOPS!!! Please remember to key in keyword to find!";

    /**
     * Constructor for IncompleteFindException.
     */
    public IncompleteFindException() {
        super(LINE + "\n" + message + "\n" + LINE);
    }
}
