package duke.exception;

/** Exception to be thrown when tasks from the file cannot be loaded */
public class LoadFileException extends DukeException {

    /**
     * Constructor for a LoadFileException
     */
    public LoadFileException() {
        super("Unable to load tasks from file.");
    }
}
