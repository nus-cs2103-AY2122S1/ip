package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * UI of the bot
 */
public class Ui {

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
     * Prints the error to the user.
     *
     * @param errorMessage The error message you wish to show to the user.
     * @return The string representing the error message.
     */
    public String showError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }

}
