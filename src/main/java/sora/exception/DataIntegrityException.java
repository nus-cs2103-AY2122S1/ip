package sora.exception;

/**
 * Throws when data integrity of the storage file is compromised.
 *
 * @author Zhang Shi Chen
 */
public class DataIntegrityException extends SoraException {
    public DataIntegrityException() {
        super("Storage file integrity compromised :(");
    }
}
