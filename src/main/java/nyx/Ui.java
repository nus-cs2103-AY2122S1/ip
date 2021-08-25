package nyx;

import java.util.Scanner;

/**
 * Deals with interactions with the user,
 * which include operations such as reading in user input and displaying outputs.
 */
public class Ui {
    private static final String LS = System.lineSeparator(); // A platform independent line separator
    private final Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a start message to the user using the specified logo.
     * @param logo The logo to display.
     */
    public void displayStart(String logo) {
        displayOutput("Hello! This is\n" + logo + "\nWhat can I do for you?");

    }

    private void displayDivider() {
        System.out.println("\t____________________________________________________________" + LS);
    }

    /**
     * Display the specified message to the user.
     * @param message The message to show the user.
     */
    public void displayOutput(String message) {
        displayDivider();
        System.out.println("\t" + message.replace("\n", LS + "\t"));
        displayDivider();
    }

    /**
     * Reads in a line of input from the user.
     * @return String representation of the user input.
     */
    public String readInput() {
        return sc.nextLine();
    }
}
