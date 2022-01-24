package duke;

import java.util.Scanner;

/**
 * Ui handles displaying information and reading input from the user.
 *
 * @author Ho Wen Zhong
 */
public class Ui {

    /**
     * Displays the Duke logo.
     */
    public static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    /**
     * Displays loading error.
     */
    public void showLoadingError() {
        String errorMessage = "Encountered an error loading task list from file.\n";
        System.out.println(errorMessage);
    }

    /**
     * Welcomes the user.
     */
    public void showWelcome() {
        displayLogo();
        greet();
    }

    /**
     * Reads user input.
     * @return User input as String.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    /**
     * Displays a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays an error message.
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a response.
     * @param message The message to be displayed.
     */
    public String showResponse(String message) {
        System.out.println(message);
        return message;
    }
}
