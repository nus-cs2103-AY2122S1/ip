package duke.exception;

/**
 * Thrown when data integrity of the storage file is compromised.
 *
 * @author Zhang Shi Chen
 */
public class DataIntegrityException extends DukeException {
    public DataIntegrityException() {
        super("Storage file integrity compromised :(");
    }
}
