package main.java.duke;
import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    public Scanner getScanner() {
        return this.sc;
    }

    /**
     * Shows welcome message and creates scanner for user input.
     */
    public void showWelcome() {
        System.out.println("Hello from Neko!\nWhat can I do for you?\n");
        sc = new Scanner(System.in);
    }

    /**
     * Reads user input from scanner.
     * 
     * @return the user input as string
     */
    public String readCommand() {
        String userInput = sc.nextLine();
        return userInput;
    }

    public void showLine() {
        System.out.println("--------------------------");
    }

    /**
     * Displays the error message.
     * 
     * @param error the error message
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays loading error message.
     */
    public void showLoadingError() {
        System.out.println("Oops! Error in loading the document...");
    }
}
