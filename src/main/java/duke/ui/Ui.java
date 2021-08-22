package duke.ui;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "____________________________________________________________\n"
                     + "Hello! I'm Duke\n"
                     + "What can I do for you?\n"
                     + "____________________________________________________________";
        System.out.println(greet);
    }

    public void showLoadingError() {
        System.out.println("An error occurred when loading file.");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showAdded() {
        System.out.println("New command added");
    }
}
