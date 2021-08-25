package duke.exceptions;

public class NoTimeException extends DukeException {
    public NoTimeException() {
        super("Please provide a timeline for your task! ☹");
    }
}
