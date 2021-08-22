package duke.exception;

public class DukeMissingDateTimeException extends DukeException {
    public DukeMissingDateTimeException() {
        super("Error: Date/Time is missing!");
    }
}
