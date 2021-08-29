package duke;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";

    public Ui() {
    }

    public void toScreen(String... msgs) {
        System.out.println(INDENT + LINE);
        for (String msg : msgs) {
            System.out.println(INDENT + msg);
        }
        System.out.println(INDENT + LINE);
    }

    public void firstWelcome() {
        toScreen("Hello, I'm Duke!", "How can I help you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
