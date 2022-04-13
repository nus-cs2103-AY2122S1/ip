package duke.exception;

/** Parent class of all the Exceptions in Duke */
public class DukeException extends Exception {

    /**
     * Constructor for a DukeException
     * @param errorMessage Error message for the Exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
