package duke.exception;

public class EventException extends DukeException {
    public EventException() {
        super("The description of an event cannot be empty.");
    }
    public EventException(String str) {
        super(str);
    }
}
