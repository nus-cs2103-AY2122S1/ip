package Pix.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Constructor of the Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message for the user.
     */
    public void displayWelcome() {
        System.out.println("This is Pix. Why did you summon me AGAIN...\nWhat do want now?");
    }

    /**
     * Registers the user's next command.
     *
     * @return Returns the next command.
     */
    public String nextCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message Message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows a loading error if the file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("I can't load your file, it's as bad as you!");
    }

    /**
     * Displays the error message on the Ui.
     *
     * @param errorMessage Message to be displayed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}