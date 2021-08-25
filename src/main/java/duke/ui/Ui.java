package duke.ui;

import java.util.Scanner;

/**
 * Represents a Ui interface to deal with user interactions.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Ui {
    private final static String lineBreak = "========================================================================";
    private Scanner sc;

    /**
     * Constructor for a Storage object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the logo, together with a greeting.
     */
    public void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Outputs a linebreak for easier formatting.
     */
    public void printLineBreak() {
        System.out.println(lineBreak);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    /**
     * Says goodbye to the user.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
