package duke;

/**
 * This class represents the response from duke to gui.
 */
public class Response {
    private String message;
    private boolean isExit;

    /**
     * Constructs a response instance using the given message and is exit state.
     *
     * @param message The given message.
     * @param isExit The given is exit state.
     */
    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Returns the message.
     *
     * @return The message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the is exit state.
     *
     * @return The is exit state.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
