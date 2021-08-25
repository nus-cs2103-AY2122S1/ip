package ponyo.ui;

import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    private static final String CMD_DIVIDER = "\t____________________________________________________________";

    private static final String LOGO =
            "    ____    ____    ____    __  __   ____ \n" +
            "   / __ \\  / __ \\  / __ \\  / / / /  / __ \\\n" +
            "  / /_/ / / /_/ / / / / / / /_/ /  / /_/ /\n" +
            " / .___/  \\____/ /_/ /_/  \\__, /   \\____/ \n" +
            "/_/                      /____/           \n";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Generates and prints the welcome message at the start of the application.
     */
    public void showWelcomeMessage() {
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
     * Read the next command input by user.
     *
     * @return user's input command
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints a line divider after every command.
     */
    public void showLine() {
        show(CMD_DIVIDER);
    }

    /**
     * Prints the error that has occurred.
     *
     * @param msg the error message provided
     */
    public void showError(String msg) {
        show(msg);
    }

    /**
     * Shows message(s) to user.
     *
     * @param msg the messages to be printed
     */
    public void show(String... msg) {
        for (String m : msg) {
            out.println(m);
        }
    }
}