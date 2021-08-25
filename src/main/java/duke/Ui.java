package duke;

import java.util.Scanner;

/**
 * Encapsulates the user interface for Duke.
 *
 * @author Hanif Kamal
 */
public class Ui {
    private Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message.
     */
    public void welcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Gets the next line of user input.
     * @return A String representing the next line of user input.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints a goodbye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}