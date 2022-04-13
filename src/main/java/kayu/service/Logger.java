package kayu.service;

/**
 * Communicates to the user on the command line through {@link kayu.Kayu}.
 */
public class Logger {

    private static final String LOGO = "\n"
            + " /$$   /$$  /$$$$$$  /$$     /$$ /$$   /$$\n"
            + "| $$  /$$/ /$$__  $$|  $$   /$$/| $$  | $$\n"
            + "| $$ /$$/ | $$  \\ $$ \\  $$ /$$/ | $$  | $$\n"
            + "| $$$$$/  | $$$$$$$$  \\  $$$$/  | $$  | $$\n"
            + "| $$  $$  | $$__  $$   \\  $$/   | $$  | $$\n"
            + "| $$\\  $$ | $$  | $$    | $$    | $$  | $$\n"
            + "| $$ \\  $$| $$  | $$    | $$    |  $$$$$$/\n"
            + "|__/  \\__/|__/  |__/    |__/     \\______/ \n";

    private static final String LINE_SPLIT = "____________________________________"
            + "_______________________________________";

    /**
     * Prints logo.
     */
    public void printLogo() {
        printMessage(LOGO);
    }

    /**
     * Displays the messaged wrapped with {@link #LINE_SPLIT}.
     *
     * @param message Message to display within {@link #LINE_SPLIT}.
     */
    public void printMessage(String message) {
        print(message + '\n' + LINE_SPLIT);
    }

    /**
     * Uses the default system's println method to output the message string.
     *
     * @param string String to print in standard format.
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Generates the error template which is then displayed to the user.
     *
     * @param errorMessage Error message to display.
     */
    public void printError(String errorMessage) {
        printMessage("Error: " + errorMessage);
    }
}
