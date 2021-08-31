package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * UI of the bot
 */
public class Ui {

    /**
     * Decorative border for bot output
     **/
    public static final String BORDER = "____________________________________________________________";

    /**
     * Decorative prefix for bot output
     **/
    public static final String PREFIX = "\t";

    /**
     * Input component of the UI
     **/
    private final Scanner sc;

    /**
     * Output component of the UI
     **/
    private final PrintStream out;

    /**
     * Initializes the UI.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints text to the user.
     *
     * @param message The text you wish to show to the user.
     */
    public void printToUser(String message) {
        out.println(PREFIX + message);
    }

    /**
     * Prints decorative border to the user.
     */
    public void printBorder() {
        out.println(PREFIX + BORDER);
    }

    /**
     * Prints associated text when user starts the bot.
     */
    public void welcome() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        printBorder();
        out.println(logo);
        printToUser("Hello! I'm Duke.");
        printToUser("What can I do for you?");
        printBorder();
    }

    /**
     * Prompts the user for a command and reads it.
     *
     * @return String representing the command that the user inputted.
     */
    public String readCommand() {
        out.print("(User) Enter command: ");
        String fullCommand = sc.nextLine().trim();
        printToUser("");
        return fullCommand;
    }

    /**
     * Prints the error to the user.
     *
     * @param errorMessage The error message you wish to show to the user.
     * @return The string representing the error message.
     */
    public String showError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }

}
