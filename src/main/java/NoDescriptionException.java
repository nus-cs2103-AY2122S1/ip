public class NoDescriptionException extends DukeException {
    public NoDescriptionException() {
        super("A description of the task is required!");
    }
}
