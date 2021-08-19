package duke.exceptions;

/**
 * This is a duke.exceptions.DukeFileException class that extends duke.exceptions.DukeException.
 */
public class DukeFileException extends DukeException {

    public DukeFileException() {
        super("");
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! I might have some issues dealing with files.";
    }
}
