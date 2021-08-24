package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    private final String LOGO = " ____        _\n"
        + "|  _ \\ _   _| | _____\n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Oh no, looks like you don't have a existing list! " +
                "Lets help you create a new one!");
    }
}
