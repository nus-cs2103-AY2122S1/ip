package duke.exceptions;

public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException() {
        super("You have already created this task!");
    }
}
