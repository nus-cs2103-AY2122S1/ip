/**
 * A Response from the application to some user input.
 */
public class Response {
    public static String BASE_INDENT = "    ";
    public static String MESSAGE_INDENT = " ";

    public static String LINE = "____________________________________________________________";

    private String message;

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
        this.message = String.join(System.lineSeparator(), messages);
    }

    /**
     * Adds the specified message to the response.
     * @param message Message to be added.
     */
    public void add(String message) {
        this.message = this.message.equals("")
            ? message
            : this.message + System.lineSeparator() + message;
    }

    /**
     * Converts the Response to its String representation.
     * @return The String representation of the Response.
     */
    @Override
    public String toString() {
        String[] messageLines = this.message.split(System.lineSeparator());

        int numberOfOutputLines = messageLines.length + 2;
        String[] outputLines = new String[numberOfOutputLines];

        int messageOffset = 1;
        outputLines[0] = Response.BASE_INDENT + Response.LINE;
        outputLines[numberOfOutputLines - 1] = Response.BASE_INDENT + Response.LINE;

        for (int index = 0; index < messageLines.length; index ++) {
            outputLines[index + messageOffset] = Response.BASE_INDENT + Response.MESSAGE_INDENT + messageLines[index];
        }

        return String.join(System.lineSeparator(), outputLines);
    }
}
