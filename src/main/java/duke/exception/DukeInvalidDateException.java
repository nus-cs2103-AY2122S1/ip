package duke.exception;

public class DukeInvalidDateException extends DukeException {
    public DukeInvalidDateException() {
        super("Invalid Date @_@\nDate formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd");
    }
}
