package duke.exception;

import duke.exception.DukeException;

/**
 * Represents the specific exception that has the index out of range.
 *
 * @author QIN GUORUI
 */
public class OutOfRangeException extends DukeException {
    public OutOfRangeException(String message) {
        super("The index for " + message + " is out of range,please choose another one.");
    }
}
