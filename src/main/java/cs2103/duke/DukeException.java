package cs2103.duke;

/**
 * This class encapsulates the exception thrown when the user enters an erroneous input.
 */
public class DukeException extends Exception {
    private static String msg;

    public DukeException(String errorMessage) {
        super(errorMessage);
        msg = errorMessage;
    }

    @Override
    public String toString() {
        return ("Oh No! I do not understand your input due to the following problem:\n" + msg);
    }
}
