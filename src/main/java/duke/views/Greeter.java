package duke.views;

/**
 * This class is responsible for printing the initial welcome message.
 */
public class Greeter {
    private static String welcomeMessage;

    static {
        init();
    }

    /**
     * Initialises the greeter.
     */
    public static void init() {
        String hello = "Hello! I'm Duke";
        String query = "What can I do for you?";
        welcomeMessage = String.format("%s%s%s%s", hello, System.lineSeparator(), query, System.lineSeparator());
    }

    /**
     * Generates greet response.
     */
    public String greet() {
        return welcomeMessage;
    }
}
