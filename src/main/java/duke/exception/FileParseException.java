package duke.exception;

/**
 * Throws an exception when the file format is not of the format whereby
 * each line denotes the storage string representation of a task.
 *
 * @author botr99
 */
public class FileParseException extends Exception {
    /**
     * Constructs a FileParseException with the given message.
     *
     * @param message The error message.
     */
    public FileParseException(String message) {
        super(message);
    }
}
