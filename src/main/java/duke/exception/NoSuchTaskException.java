package duke.exception;

/** Just a better way of saying index out of bounds. */
public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException() {
        super("Task does not exist!");
    }
}
