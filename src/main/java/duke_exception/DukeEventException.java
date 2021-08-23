package duke_exception;
public class DukeEventException extends DukeException {
    public DukeEventException() {
        super("OOPS!!! The description of a event cannot be empty.");
    }
}
