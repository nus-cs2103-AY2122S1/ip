package duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Wraps a given message above and below with lines and prints it.
     *
     * @param msg the message to wrap and print
     */
    public void wrapPrint(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printError(String msg) {
        System.out.println("oh no! " + msg);
    }

    /**
     * Prints the welcome message on the terminal.
     */
    public void showWelcome() {
        wrapPrint("Hello! I'm Bob\nWhat can I do for you?");
    }
}
