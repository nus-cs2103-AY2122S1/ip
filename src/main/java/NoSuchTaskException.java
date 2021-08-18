public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException() {
        super("Task does not exist!");
    }
}
