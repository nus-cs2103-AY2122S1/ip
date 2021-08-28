package ui;

import java.util.Scanner;

public class Ui {
    private static final String MESSAGE_INTRO = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_OUTRO = "Bye. Come back soon!";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(MESSAGE_INTRO);
    }

    public void showGoodbye() {
        System.out.println(MESSAGE_OUTRO);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("_____________________________________\n");
    }

    public void showError(String message) {
        System.out.println(message);
    }

}
