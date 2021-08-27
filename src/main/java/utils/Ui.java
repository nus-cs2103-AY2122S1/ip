package utils;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MSG = "Hello! I'm Mr House";
    private static final String EXIT_MSG = "Goodbye Courier!";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println(formatString(WELCOME_MSG));
    }

    public void printExitMessage() {
        sc.close();
        System.out.println(formatString(EXIT_MSG));
    }

    public void printResponse(String message) {
        System.out.println(formatString(message));
    }

    public void printError(String message) {
        System.out.println(formatString(message));
    }

    public String readCommand() {
        return sc.nextLine();
    }

    private static String formatString(String message) {
        return "\t____________________________\n" +
                "\t " + message.replace("\n", "\n\t ") + "\n" +
                "\t____________________________\n";
    }
}