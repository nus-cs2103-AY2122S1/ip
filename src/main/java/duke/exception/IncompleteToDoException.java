package duke.exception;

import static duke.Ui.LINE;

/**
 * Representation for incomplete fields of ToDoObject exception.
 */
public class IncompleteToDoException extends DukeException{
    private static String MESSAGE = "OOPS!!! The description of a todo cannot be empty.";

    /**
     * Constructor for IncompleteToDoException.
     */
    public IncompleteToDoException() {
        super(LINE + "\n" + MESSAGE + "\n" + LINE);
    }
}
