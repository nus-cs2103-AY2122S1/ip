package duke.exception;

/**
 * Thrown when reading a file with invalid format.
 */
public class InvalidFileFormatException extends DukeException {
    public InvalidFileFormatException() {
        super("Invalid file format! Save file ignored!");
    }
}
