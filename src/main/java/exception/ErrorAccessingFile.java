package exception;

public class ErrorAccessingFile extends DukeException {
    public ErrorAccessingFile(String action) {
        super(String.format("Error accessing file when trying to %s", action));
    }
}
