package duke;

import java.util.Scanner;

/**
 * Class handles printing message to user
 */
public class Ui {
    Scanner myScanner;

    public Ui() {
        myScanner = new Scanner(System.in);
    }

    protected String getUserInput() {
        return myScanner.nextLine();
    }

    /**
     * Print the line
     */
    public static String printLine() {
        return "--------------------------------------------------";
    }

    public static String printBigIcon() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n" + Ui.printLine();
    }

    /**
     * Print the welcome message
     */
    public String printWelcome() {
        return this.printLine() + "\n" + "Hello! I'm Duke.Duke\nWhat can I do for you?" + "\n" + this.printLine();
    }

}
