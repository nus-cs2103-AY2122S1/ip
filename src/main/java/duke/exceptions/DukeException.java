package duke.exceptions;

/**
 * Class that handles exceptions specific to Duke
 */
public class DukeException extends Exception {
    /**
     * Constructor that initalizes a DukeException object.
     * @param message
     */
    public DukeException(String message){
        super(message);
    }
}
