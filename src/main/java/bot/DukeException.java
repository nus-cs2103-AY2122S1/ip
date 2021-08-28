package main.java.bot;

/**
 * A class that handles exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the DukeException class.
     *
     * @param errorMessage The error message given to the DukeException object, to be displayed to the user.
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! It looks like was an error handling your request!\n\nThe error is as follows:\n" + errorMessage);
    }
}
