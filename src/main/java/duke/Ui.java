package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Initialises Ui with a scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Scans for user input.
     *
     * @return a String of the user input;
     */
    public String scan() {
        return sc.nextLine();
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    /**
     * Prints an exit message.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints an error message.
     *
     * @param error the error that is caught.
     */
    public void showError(DukeException error) {
        System.err.println(error.getMessage());
    }
}
