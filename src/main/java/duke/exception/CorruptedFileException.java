package duke.exception;

/**
 * A DukeException that occurs during save file reading and writing.
 */
public class CorruptedFileException extends DukeException {

    /**
     * Public constructor to create a CorruptedFileException.
     */
    public CorruptedFileException() {
        super("Corrupted File");
    }
}
