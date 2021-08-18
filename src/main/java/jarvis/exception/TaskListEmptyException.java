package jarvis.exception;

public class TaskListEmptyException extends JarvisException {
    public TaskListEmptyException() {
        super("Please add some tasks first!");
    }
}
