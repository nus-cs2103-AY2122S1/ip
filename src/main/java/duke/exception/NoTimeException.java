package duke.exception;

public class NoTimeException extends DukeException {
    public NoTimeException() {
        super("Please specify a time for the task in YYYY-MM-DD (e.g. 2021-09-14)");
    }
}
