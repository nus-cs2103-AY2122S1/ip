package duke;

import java.util.Scanner;

/**
 * User interface class for controlling user interaction.
 * Reads user inputs and displays outputs.
 *
 * @author Chang-CH
 */
public class Ui {
    private final Scanner inputReader;

    /**
     * Sole constructor for invocation by Duke.
     */
    protected Ui() {
        super();
        inputReader = new Scanner(System.in);
    }

    /**
     * Reads user input from scanner.
     *
     * @return user input string.
     */
    protected String readCommand() {
        return inputReader.nextLine();
    }

    /**
     * Displays an error message for unknown commands
     *
     * @param error Error message to be displayed.
     */
    protected void showLoadingError(String error) {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Closes the scanner.
     */
    public void exit() {
        inputReader.close();
    }

    /**
     * Prints a line to separate outputs more clearly.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error.
     *
     * @param message Error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the welcome splash screen.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Python>Java\n"
                + "What must I do for you?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }
}
