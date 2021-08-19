/**
 * Class for exceptions related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for an exception related to Duke.
     *
     * @param errorMsg String describing error.
     */
    public DukeException(String errorMsg) {
        super("OOPS!!! " + errorMsg);
    }
}
