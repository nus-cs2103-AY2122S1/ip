package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;


    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets user with a simple message
     */
    public void greet() {
        System.out.println("Hello! I'm Magnolia\n" + "What can I do for you?");
    }

    /**
     * Gets next line of user input
     *
     * @return String of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows user a regular message
     *
     * @param message Message to be shown
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Shows user an error message
     *
     * @param message Error message to be shown
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
