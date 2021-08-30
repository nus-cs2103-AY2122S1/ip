package duke;

import java.util.Scanner;

/**
 * The class that deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * The constructor for Ui.
     *
     * @param scanner
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays a line.
     */
    public void showLine() {
        System.out.println("-------------------------------------");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("-------------------------------------");
        System.out.println("Hello there, Duke here.\nWhat can I do for you today?");
        System.out.println("-------------------------------------");
    }

    /**
     * Reads the command line that the user inputs.
     *
     * @return The command line that the user input.
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }
}
