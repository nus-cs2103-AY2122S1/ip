package duke.utils;

import duke.exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "_".repeat(60);
    private static final String[] WELCOME_MESSAGE = {"Hello! I'm Duke", "What can I do for you?"};
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String TAB = " ".repeat(4);

    public void showWelcome() {
        printOut(WELCOME_MESSAGE);
    }

    public void showError(DukeException dukeException) {
        printOut(dukeException.getMessage());
    }

    public void showLoadingError() {
        printOut("Data file is corrupted, unable to load");
    }

    public void showLoadingSuccess() {
        printOut("Task list loaded successfully!");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static void printOut(String... messages) {
        System.out.println(TAB + SEPARATOR);
        for (String message : messages) {
            System.out.println(TAB + message);
        }
        System.out.println(TAB + SEPARATOR);
    }
}
