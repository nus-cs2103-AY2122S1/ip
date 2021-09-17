package duke.main;

/**
 * This class is for representing any errors the Duke application may face.
 */
public class DukeException extends Exception {

    /**
     * The class Constructor for DukeException that takes in a message explaining the error.
     *
     * @param message The message explaining the error that has occurred.
     */
    public DukeException(String message) {
        super(message);
    }
}
