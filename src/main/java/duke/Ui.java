package duke;

import java.util.Scanner;

public class Ui {

    private final Scanner reader;

    public Ui() {
        reader = new Scanner(System.in);
    }

    public void showWelcome() {
        String welcomeMessage = "Hello! I'm duke.Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    public void stringWithDivider(String text) {
        System.out.println("_______________________________________________________________");
        System.out.println(text);
        System.out.println("_______________________________________________________________");
    }

    public String readCommand() {
        return reader.nextLine();
    }
}
