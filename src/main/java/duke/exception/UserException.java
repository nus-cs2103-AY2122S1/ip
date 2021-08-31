package duke.exception;

import duke.Response;

/**
 * A duke.exception.UserException represents an Exception thrown because of an invalid input by the User.
 */
public class UserException extends Exception {
    private final String message;

    /**
     * Create a duke.exception.UserException with the specified Message.
     * @param message The message to be displayed in the duke.Response.
     */
    public UserException(String message) {
        this.message = message;
    }

    /**
     * Returns the duke.Response to be printed to the console of this duke.exception.UserException.
     */
    public Response toResponse() {
        return new Response(new String[]{ "☹ OOPS!!!", this.message });
    }
}
