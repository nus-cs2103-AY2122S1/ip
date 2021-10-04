package seedu.duke.exceptions;

public class DukeException extends RuntimeException {

    private final String err;

    /**
     * Primary Constructor for Duke Exception.
     * 
     * @param err is the error message.
     */
    public DukeException(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return this.err;
    }

}
