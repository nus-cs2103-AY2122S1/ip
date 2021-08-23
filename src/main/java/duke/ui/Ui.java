package duke.ui;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public Ui() {}

    public void greet() {
        System.out.println("Hello, I\'m  JARVIS");
        System.out.println("What can I do for you?");
    }

    public String getUserInput() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String input = sc.nextLine();

        return input;
    }

    public void say(String msg) {
        System.out.println(msg);
    }
}
