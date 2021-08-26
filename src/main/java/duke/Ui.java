package duke;

import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {

    private static String template = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
    private Scanner sc;

    /**
     * Constructs the Deadline object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a message with the template.
     *
     * @param message Message String to be printed.
     */
    public void printMessage(String message) {
        System.out.println(template + "\n" + message + template);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String startText = "Hello! I'm duke.Duke\n" + "What can I do for you?";
        System.out.println(startText);
    }

    /**
     * Reads the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
