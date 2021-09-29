package duke.exception;

import duke.Response;

/**
 * A UserException represents an Exception thrown because of an invalid input by the User.
 */
public class UserException extends Exception {
    private final String message;

    /**
     * Create a UserException with the specified Message.
     * @param message The message to be displayed in the Response.
     */
    public UserException(String message) {
        this.message = message;
    }

    /**
     * Create a UserException with the specified Message array.
     * @param messages The messages to be displayed in the Response.
     */
    public UserException(String[] messages) {
        this.message = String.join(System.lineSeparator(), messages);
    }

    /**
     * Returns the Response to be printed to the console of this UserException.
     */
    public Response toResponse() {
        return new Response(new String[]{ "OOPS!!!", this.message });
    }
}
