package duke;

import java.util.Scanner;

/**
 * Represents a User Interface that handles interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a User Interface.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows the message of an error on start up of Duke.
     *
     * @param e The exception thrown on start up.
     */
    public void showStartUpError(DukeException e) {
        System.out.println((new DukeException("Error starting up.").concat(e)));
    }

    /**
     * Shows a welcome message.
     */
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

    /**
     * Shows a divider line.
     */
    public void showLine() {
        String separator = "------------------------------------------------------------------";
        System.out.println(separator);
    }

    /**
     * Reads the user's input command and returns it.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows a message to the user.
     *
     * @param message The message to be shown.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}