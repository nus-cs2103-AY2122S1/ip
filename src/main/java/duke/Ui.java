package duke;

import dukeException.DukeException;

import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

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
        String greetMessage[] = new String[2];
        greetMessage[0] = "Hello! I'm Duke";
        greetMessage[1] = "What can I do for you?";
        printMessage(greetMessage);
    }

    /**
     * A public method to print message with certain indentation and format.
     * Receive an array of String, and output one String per line.
     *
     * @param messages
     */
    public void printMessage(String messages[]) {
        System.out.println("    ____________________________________________________________");
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            System.out.print("     ");
            System.out.println(messages[i]);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showDukeException(DukeException e) {
        this.printMessage(new String[] {e.getMessage()});
    }
}
