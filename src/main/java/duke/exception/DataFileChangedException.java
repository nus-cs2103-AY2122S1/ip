package duke.exception;

/**
 * Exception of Duke that occurs when the data file is tampered with.
 */
public class DataFileChangedException extends DukeException {
    public DataFileChangedException() {
        super("Contents of file have been changed! Resetting the list!");
    }
}
