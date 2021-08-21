package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    @Override
    public String toString() {
        return "Yiyang-bot's UI";
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void displayGreetings() {
        System.out.println("\tHello this is Yiyang-bot :D");
        System.out.println("\tWhat can I do for you?");
    }

    public void displayBye() {
        System.out.println("\tBye. Hope to see you again.");
    }

    public void showLine() {
        System.out.println("\t--------------------------------------------------");
    }

    public void showError(String errorMsg) {
        System.err.println(errorMsg);
    }
}
