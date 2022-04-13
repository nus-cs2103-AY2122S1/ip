package duke;

/**
 * Class for exceptions related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs an exception related to Duke.
     *
     * @param errorMsg String describing error.
     */
    public DukeException(String errorMsg) {
        super("OOPS!!! " + errorMsg);
        assert !errorMsg.isEmpty() : "errorMsg should not be empty";
    }
}
