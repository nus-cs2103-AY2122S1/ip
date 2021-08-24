package jarvis.exception;

public class InvalidStorageTaskException extends JarvisException {
    public InvalidStorageTaskException() {
        super("Invalid task in storage file!");
    }
}
