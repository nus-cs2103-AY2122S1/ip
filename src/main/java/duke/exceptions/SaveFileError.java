package duke.exceptions;

public class SaveFileError extends DukeException {
    /**
     * Occurs when there's an error with saving the file.
     */
    public SaveFileError() {
        super ("Canw't save to a filew that doesn't exist, sorry!");
    }
}