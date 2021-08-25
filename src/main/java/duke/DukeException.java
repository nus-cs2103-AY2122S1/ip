package duke;

/**
 * Encapsulates exceptions related to Duke, arising from incorrect user inputs.
 *
 * @author Hanif Kamal
 */
public class DukeException extends Exception {

    /**
     * Class constructor to initialize a DukeException instance.
     *
     * @param message An error message prompting the user to correct their inputs.
     */
    public DukeException(String message) {
        super(message);
    }
}
