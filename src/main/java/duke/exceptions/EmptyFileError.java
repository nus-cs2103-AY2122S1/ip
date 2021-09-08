package duke.exceptions;

/**
 * Class that handles empty file errors.
 *
 */
public class EmptyFileError extends DukeException {
    /**
     * Occurs when Duke tries to read txt file for Task list, but comes up empty.
     */
    public EmptyFileError() {
        super("Uwu! Pre-existing File not foundw! "
                + "Creating new file for you. . .");
    }
}