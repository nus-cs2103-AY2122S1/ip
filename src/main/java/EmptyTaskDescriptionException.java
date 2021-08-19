public class EmptyTaskDescriptionException extends DukeException {
    public EmptyTaskDescriptionException() {
        super("Description of a task cannot be empty!");
    }
}
