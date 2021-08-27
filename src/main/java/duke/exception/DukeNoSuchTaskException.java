package duke.exception;

public class DukeNoSuchTaskException extends DukeException {
    public DukeNoSuchTaskException() {
        super("Task doesn't exist! :o Check your index input ~");
    }
}
