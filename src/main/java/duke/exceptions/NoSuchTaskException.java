package duke.exceptions;

public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException() {
        super("duke.tasks.Task does not exist!");
    }
}
