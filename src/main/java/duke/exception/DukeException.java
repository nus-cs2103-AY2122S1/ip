package duke.exception;

/**
 * Throws exception specific to Duke
 */
public class DukeException extends Exception {

    /**
     * Constructs DukeException object
     *
     */
    public DukeException() {
        super("");
    }

    public String getError() {
        return "OOPS!!! Sorry something went wrong :(";
    }
}
