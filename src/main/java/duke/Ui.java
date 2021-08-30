package duke;

import java.util.Scanner;

import duke.exception.DukeException;

public class Ui {
    /** A Scanner object to read user command. **/
    private final Scanner sc;

    /**
     * A public constructor to initialized the Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * A method to print the Duke logo.
     */
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * A public method to output the greeting message.
     */
    public void greet() {
        printMessage(
            "Hello! I'm duke.Duke",
            "What can I do for you?");
    }

    /**
     * A public method to print message with certain indentation and format.
     * Receive an array of String, and output one String per line.
     *
     * @param messages The given messages to be print, one message per row.
     */
    public void printMessage(String ...messages) {
        System.out.println("    ____________________________________________________________");
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            System.out.print("     ");
            System.out.println(messages[i]);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * A method to read user command.
     *
     * @return A String, user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * A method to print the message of DukeException.
     *
     * @param e The given DukeException to be shown.
     */
    public void showDukeException(DukeException e) {
        this.printMessage(e.getMessage());
    }
}
