/**
 * This is a DukeFileException class that extends DukeException.
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
