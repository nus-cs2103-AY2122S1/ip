package ponyo.ui;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    private static final String CMD_DIVIDER = "\t____________________________________________________________";

    private static final String LOGO =
            "    ____    ____    ____    __  __   ____ \n"
                    + "   / __ \\  / __ \\  / __ \\  / / / /  / __ \\\n"
                    + "  / /_/ / / /_/ / / / / / / /_/ /  / /_/ /\n"
                    + " / .___/  \\____/ /_/ /_/  \\__, /   \\____/ \n"
                    + "/_/                      /____/           \n";

    /**
     * Generates and prints the welcome message at the start of the application.
     */
    public static void showWelcomeMessage() {
        show(LOGO,
                "Hello! I'm Ponyo.",
                "What can I do for you?",
                DIVIDER);
    }

    /**
     * Prints the error when error loading file.
     */
    public void showLoadingError() {
        show("There was an error while loading your tasks.");
    }

    /**
     * Prints a line divider after every command.
     */
    public static void showLine() {
        show(CMD_DIVIDER);
    }

    /**
     * Prints the error that has occurred.
     *
     * @param msg the error message provided
     */
    public static void showError(String msg) {
        show(msg);
    }

    /**
     * Shows messages to user.
     *
     * @param messages the messages to be printed
     * @return an array of strings to be printed in order
     */
    public static String[] show(String... messages) {
        return messages;
    }

    /**
     * Shows a message to user.
     *
     * @param msg the message to be printed
     * @return an array of a string to be printed
     */
    public static String[] show(String msg) {
        return new String[] { msg };
    }
}
