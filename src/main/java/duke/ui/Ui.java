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
    public static String intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Greets the user.
     */
    public static String greet() {
        return "Hello! I'm Faker!" + '\n' + "What can I do for you?";
    }

    /**
     * Says goodbye to the user.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the message.
     *
     * @param message Message to be printed.
     */
    public static String printMessage(String message) {
        return message;
    }
}
