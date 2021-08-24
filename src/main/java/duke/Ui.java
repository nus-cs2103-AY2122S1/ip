package duke;

import java.util.Scanner;

/**
 * Representation of the user interface of Duke.
 *
 * @author Joshua Yong
 */
public class Ui {

    private Scanner sc;

    /**
     * Class constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void hello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints the given message.
     *
     * @param message The given message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns the user input.
     * 
     * @return The user input.
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints the farewell message.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
