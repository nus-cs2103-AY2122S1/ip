package duke;

/**
 * A Response from the application to some user input.
 */
public class Response {
    private static final String BASE_INDENT = "    ";

    private String message;
    private boolean isExitResponse = false;

    /**
     * Creates a response with an empty String message.
     */
    public Response() {
        this("");
    }

    /**
     * Creates a response with the specified String message.
     * @param message The Response message.
     */
    public Response(String message) {
        this(new String[]{ message });
    }

    /**
     * Creates a response with the specified String array.
     * @param messages The Response message array.
     */
    public Response(String[] messages) {
        assert messages != null : "Messages should not be null";
        this.message = String.join(System.lineSeparator(), messages);
    }

    /**
     * Adds the specified message to the response.
     * @param messages Message to be added.
     */
    @SafeVarargs
    public final void add(String ... messages) {
        for (String message : messages) {
            this.message = this.message.equals("")
                ? message
                : this.message + System.lineSeparator() + message;
        }
    }

    /**
     * Converts the Response to its String representation.
     * @return The String representation of the Response.
     */
    @Override
    public String toString() {
        String[] messageLines = this.message.split(System.lineSeparator());
        String[] outputLines = new String[messageLines.length];

        for (int index = 0; index < messageLines.length; index++) {
            outputLines[index] = Response.BASE_INDENT + messageLines[index];
        }

        return String.join(System.lineSeparator(), outputLines);
    }

    /**
     * Sets the response to an Exit Response.
     */
    public void setExitResponse() {
        isExitResponse = true;
    }

    /**
     * Returns a boolean indicating whether to exit the Duke window.
     * @return The boolean indicating whether to exit the Duke window.
     */
    public boolean isExitResponse() {
        return isExitResponse;
    }
}
