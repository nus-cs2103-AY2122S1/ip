package exception;

/**
 * Encapsulates an exception when there is an error accessing a storage file.
 */
public class ErrorAccessingFile extends DukeException {
    public ErrorAccessingFile(String action) {
        super(String.format("Error accessing file when trying to %s", action));
    }
}
