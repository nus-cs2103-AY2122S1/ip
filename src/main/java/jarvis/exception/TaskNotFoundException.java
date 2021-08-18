package jarvis.exception;

public class TaskNotFoundException extends JarvisException {
    public TaskNotFoundException() {
        super("Please enter a valid task number!");
    }
}
