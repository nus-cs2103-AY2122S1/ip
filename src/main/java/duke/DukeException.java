package duke;

/**
 * Represents an exception from Duke
 */
public class DukeException extends Exception {

    /**
     * Constructs an instance of <code>DukeException</code>
     * @param errorMessage Error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "OOPS!! " + this.getMessage();
    }
}
