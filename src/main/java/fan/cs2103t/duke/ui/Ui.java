package fan.cs2103t.duke.ui;

import java.util.Scanner;

/**
 * Represents a UI manager for Duke to manage front-end interactions with users.
 */
public class Ui {

    public static final String LOGO = "  ____        _        \n"
            + " |  _ \\ _   _| | _____ \n"
            + " | | | | | | | |/ / _ \\\n"
            + " | |_| | |_| |   <  __/\n"
            + " |____/ \\__,_|_|\\_\\___|\n";
    public static final String DIVIDER = "____________________________________________________________";
    public static final String INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :-(";

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
        String output = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(DIVIDER + "\n");
        return output;
    }

    /**
     * Displays a goodbye message to the user through this UI and returns the goodbye message.
     *
     * @return the goodbye message
     */
    public String dismiss() {
        String byeMessage = "Bye. Hope to see you again soon!";
        displayText(byeMessage);
        return byeMessage;
    }

    /**
     * Reads one input line from the user.
     *
     * @return the one-line input from the user as a string.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Displays the specified error message to the user through this UI.
     *
     * @param message the error message to be displayed.
     */
    public void showError(String message) {
        displayText(message);
    }

    /**
     * Displays the specified text to the user through this UI.
     *
     * @param text the text to be displayed.
     */
    public void displayText(String text) {
        System.out.println(DIVIDER);
        System.out.println(text);
        System.out.println(DIVIDER + "\n");
    }

}
