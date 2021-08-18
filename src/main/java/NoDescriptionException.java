public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String task) {
        super("A description of the " + task + " is required!");
    }
}
