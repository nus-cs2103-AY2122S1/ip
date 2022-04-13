package duke;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Handles the tasks of scanning in user inputs and displaying responses to the console.
 */
public class Ui {
    static final String OPENING_MESSAGE = "I'm Duke Nukem.\n"
            + "What can I do for you kid?\n";
    static final String CLOSING_MESSAGE = "See you around kid!\n";
    static final String LINE_SEPARATOR = "____________________________________________________________";
    private final Scanner sc;


    /**
     * Initialises a new instance of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.printMessage(OPENING_MESSAGE);
    }

    /**
     * Scans in the next line of user input and converts to a String which is returned.
     *
     * @return The user input converted to a String.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Takes in a DukeException and wraps its message around with line separators before printing
     * the error message to console.
     *
     * @param de The given DukeException.
     */
    public void showError(DukeException de) {
        System.out.println(LINE_SEPARATOR + "\n" + de.getMessage() + "\n" + LINE_SEPARATOR);
    }

    /**
     * Takes in a String and wraps it in line separators before printing the result to console.
     *
     * @param string The given String.
     */
    public void printMessage(String string) {
        System.out.println(LINE_SEPARATOR + "\n" + string + LINE_SEPARATOR);
    }

    /**
     * Exits the UI.
     */
    public void exit() {
        sc.close();
    }
}
