package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /** Error message shown on start up of Duke */
    public void showStartUpError(DukeException e) {
        System.out.println((new DukeException("Error starting up.").concat(e)));
    }

    /** Welcome message shown on start up of Duke */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        showLine();
    }

    /** Show divider line */
    public void showLine() {
        String separator = "------------------------------------------------------------------";
        System.out.println(separator);
    }

    /** Takes in input from user and returns it */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Displays a message to the user */
    public void showMessage(String message) {
        System.out.println(message);
    }
}