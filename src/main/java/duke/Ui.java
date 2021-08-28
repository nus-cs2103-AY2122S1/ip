package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;


    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm Magnolia\n" + "What can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
