package ui;

import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public String showWelcome() {
        return "Hello! I'm Duke. "
                + "\nWhat can I do for you?";
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public String showError(String errorMessage) {
        return errorMessage;
    }
}
