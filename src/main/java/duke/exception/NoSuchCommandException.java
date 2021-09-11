package duke.exception;

/**
 * This exception is thrown when
 * an invalid command is input.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class NoSuchCommandException extends DukeException {

    /**
     * Returns an error message to highlight that
     * user input is not recognised.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("I'm sorry, but I don't know what that means :-(");
    }
}
