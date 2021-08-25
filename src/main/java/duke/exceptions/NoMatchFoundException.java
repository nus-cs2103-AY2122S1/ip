package duke.exceptions;

public class NoMatchFoundException extends DukeException {
    public NoMatchFoundException() {
        super("There are no related tasks in your list! ☹");
    }
}
