package duke.exceptions;

/**
 * Encapsulates the CorruptedFileException thrown by the Duke bot.
 */
public class CorruptedFileException extends DukeException {
    /**
     * Constructor for CorruptedFileException.
     */
    public CorruptedFileException() {
        super("Sorry! Your duke.txt file is corrupted and cannot be loaded!");
    }

}
