package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui (Scanner scanner) {
        this.scanner = scanner;
    }
    public void showLine() {
        System.out.println("-------------------------------------");
    }

    public void showWelcome() {
        System.out.println("-------------------------------------");
        System.out.println("Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?");
        System.out.println("-------------------------------------");
    }

    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }
}
