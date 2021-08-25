package duke;

import java.util.Scanner;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    public String readCommand() {
        // TODO: close() scanner
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        return next;
    }

    public void showLoadingError() {
        System.err.println("☹ OOPS!!! Seems like your data is corrupted. " +
                "Please make sure you data file has the correct format.");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println(logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        this.showLine();
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
