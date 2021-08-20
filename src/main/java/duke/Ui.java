package duke;

import duke.command.Command;

import java.util.Scanner;

public class Ui {
    private Scanner inputReader;
    public Ui() {
        super();
        inputReader = new Scanner(System.in);
    }
    public String readCommand() {
        return inputReader.nextLine();
    }

    public void showLoadingError(String error) {
//        System.out.println(error);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void exit() {
        inputReader.close();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Python>Java\n"
                + "What must I do for you?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }

}
