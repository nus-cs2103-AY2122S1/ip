package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public String getNextLine() {
        return scanner.nextLine();
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}