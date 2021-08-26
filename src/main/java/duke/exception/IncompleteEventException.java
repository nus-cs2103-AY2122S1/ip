package duke.exception;

import static duke.Ui.LINE;

/**
 * Representation for incomplete fields of Event exception.
 */
public class IncompleteEventException extends DukeException{
    private static String MESSAGE = "OOPS!!! The description or date of an event cannot be empty.";

    /**
     * Constructor for IncompleteEventException.
     */
    public IncompleteEventException() {
        super(LINE + "\n" + MESSAGE + "\n" + LINE);
    }
}
