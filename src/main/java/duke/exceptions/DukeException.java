package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     *
     * @param message a customized message to set for this exception
     */
    public DukeException(String message) {
        super("Rasengan!! " + message);
    }
}
