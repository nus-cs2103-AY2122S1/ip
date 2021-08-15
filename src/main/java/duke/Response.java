package duke;

/**
 * Encapsulates a response of the bot.
 */
public class Response {
    private static final String PARTITION = "––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private final String message;

    public Response(String message) {
        this.message = message;
    }

    /**
     * Prints the response in the system's output.
     */
    public void print() {
        System.out.println(ANSI_CYAN + PARTITION + "\n" + message + "\n" + PARTITION + ANSI_RESET);
    }
}
