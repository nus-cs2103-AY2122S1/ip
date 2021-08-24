package duke;

import duke.exception.DukeException;

import java.util.Scanner;

public class Ui {
    private final String OPENING_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        System.out.println(OPENING_MESSAGE);
    }

    /**
     * Scans in the next line of the user's input and returns it as a String.
     *
     * @return The user's input converted to a String.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Takes in a DukeException and prints its error message.
     *
     * @param de The given DukeException.
     */
    public void showError(DukeException de) {
        System.out.println(de.getMessage());
    }

    /**
     * Exits the UI.
     */
    public void exit() {
        sc.close();
        System.out.println(CLOSING_MESSAGE);
    }
}
