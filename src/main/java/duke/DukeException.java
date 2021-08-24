package duke;

/**
 * Special Exception class for Duke.java.
 */
public class DukeException extends Exception {
    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
