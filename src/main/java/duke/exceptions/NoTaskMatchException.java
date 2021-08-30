package duke.exceptions;

public class NoTaskMatchException extends DukeException {
    public NoTaskMatchException() {
        super("You have no task matching this description!");
    }
}
