package duke.exceptions;

public class DukeException extends Exception {

    /**
     * DukeException constructor.
     *
     * @param errorMessage Message to be shown to user when this exception occurs.
     */
    public DukeException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
    }

    /**
     * Returns the String representation of the Exception.
     *
     * @return String representation of the Exception.
     */
    @Override
    public String toString() {
        return String.format("OOPS!!! %s", super.getMessage());
    }
}
