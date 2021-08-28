package duke.exception;

public class DataIntegrityException extends DukeException {
    public DataIntegrityException() {
        super("Storage file integrity compromised :(");
    }
}
