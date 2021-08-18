public class DukeException extends Exception {
    /**
     * A default constructor for this DukeException.
     */
    public DukeException() {

    }

    /**
     * A constructor for this DukeException.
     *
     * @param errorMessage the message describing this exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
