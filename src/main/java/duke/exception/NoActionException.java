package duke.exception;

import duke.exception.DukeException;

public class NoActionException extends DukeException {
    public NoActionException(String errorMessage) {
        super(errorMessage);
    }
}
