package duke;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("There was an error reading the saved file :(");
    }


}
