package duke;

import java.util.Scanner;

/**
 * Class handles printing message to user
 */
public class Ui {
    private Scanner myScanner;

    public Ui() {
        myScanner = new Scanner(System.in);
    }

    protected String getUserInput() {
        return myScanner.nextLine();
    }

    /**
     * Return the line
     */
    public static String printLine() {
        return "--------------------------------------------------";
    }

    /**
     * Return the welcome message
     */
    public String printWelcome() {
        return this.printLine() + "\n" + "Hello! I'm Duke\nWhat can I do for you?" + "\n" + this.printLine();
    }

    /**
     * Print the response message
     *
     * @param response
     */
    public void printResponse(Response response) {
        String message = Ui.printLine() + "\n" + response + "\n" + Ui.printLine();
        System.out.println(message);
    }

}
