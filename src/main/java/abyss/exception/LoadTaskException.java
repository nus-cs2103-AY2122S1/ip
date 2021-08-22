package abyss.exception;

public class LoadTaskException extends AbyssException {
    public LoadTaskException(String message) {
        super("Error loading tasks: " + message);
    }
}
