package fan.cs2103t.duke.ui;

import java.util.Scanner;

/**
 * Represents a UI manager for Duke to manage front-end interactions with users.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a UI manager for Duke.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user through this UI and returns the greeting message.
     *
     * @return the greeting message
     */
    public String greet() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Displays a goodbye message to the user through this UI and returns the goodbye message.
     *
     * @return the goodbye message
     */
    public String dismiss() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads one input line from the user.
     *
     * @return the one-line input from the user as a string.
     */
    public String readLine() {
        return scanner.nextLine();
    }

}
