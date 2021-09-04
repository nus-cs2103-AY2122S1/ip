package duke.exception;

/**
 * A class that represents exceptions unique to duke.Duke.
 * A DukeException is thrown when a given input is not recognised a valid
 * input for duke.Duke.
 */
public class DukeException extends Exception {

    @Override
    public String getMessage() {
        return "Sorry, I don't know what that means :(";
    }
}
