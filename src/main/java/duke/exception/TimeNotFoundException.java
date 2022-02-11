package duke.exception;

public class TimeNotFoundException extends DukeException {
    public TimeNotFoundException() {
        super("Duration not indicated! Please try again.");
    }
}
