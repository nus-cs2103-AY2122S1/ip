package utils;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MSG = "Hello! I'm Mr House";
    private static final String EXIT_MSG = "Goodbye Courier!";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints formatted welcome message on the console
     */
    public void printWelcomeMessage() {
        System.out.println(formatString(WELCOME_MSG));
    }

    /**
     * Prints formatted exit message on the console
     */
    public void printExitMessage() {
        sc.close();
        System.out.println(formatString(EXIT_MSG));
    }

    /**
     * Prints formatted message on the console
     */
    public void printResponse(String message) {
        System.out.println(formatString(message));
    }

    /**
     * Prints formatted error message on the console
     */
    public void printError(String message) {
        System.out.println(formatString(message));
    }

    /**
     * Returns user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    private static String formatString(String message) {
        return "\t____________________________\n" +
                "\t " + message.replace("\n", "\n\t ") + "\n" +
                "\t____________________________\n";
    }
}