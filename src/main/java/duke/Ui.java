package duke;

import java.util.Scanner;

public class Ui {
    private String line = "____________________________________________________________";
    private String indent = "    ";

    public Ui() {
    }

    public void toScreen(String... msgs) {
        System.out.println(indent + line);
        for (String msg : msgs) {
            System.out.println(indent + msg);
        }
        System.out.println(indent + line);
    }

    public void firstWelcome() {
        toScreen("Hello, I'm Duke.Duke!", "How can I help you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
