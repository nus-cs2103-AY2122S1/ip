package duke;

import duke.exception.DukeException;

import java.util.Scanner;

/**
 * Handles the tasks of scanning in user inputs and displaying responses to the console.
 */
public class Ui {
    private final Scanner sc;
    private final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Initialises a new instance of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        String OPENING_MESSAGE = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
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
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!\n";
        this.printMessage(CLOSING_MESSAGE);
    }
}
