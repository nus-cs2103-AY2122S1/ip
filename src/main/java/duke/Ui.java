package duke;

import java.util.Scanner;

/**
 * Encapsulates a class that deals with interactions from the user and displaying Duke's responses to the user.
 */
public class Ui {
    /**
     * Prints a horizontal line that serves as a top and bottom border for responses from Duke.
     */
    public void showLine() {
        System.out.println("\t ____________________________________________________________ \n");
    }

    /**
     * Prints a message that introduces Duke.
     */
    public void showWelcome() {
        showLine();
        String s = "\t Hello! I'm Duke, your personal assistant. \n"
                + "\t What can I do for you? \n";
        System.out.println(s);
        showLine();
    }

    /**
     * Prints a message from Duke.
     *
     * @param message The message to be displayed to the user.
     */
    public void showResponse(String message) {
        System.out.println("\t " + message + " \n");
    }

    /**
     * Reads the user input.
     *
     * @return The string entered by the user.
     */
    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
