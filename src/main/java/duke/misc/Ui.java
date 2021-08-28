package duke.misc;

import java.util.Scanner;

/**
 * Ui class to encapsulate display messages and handle input/output of duke.
 */
public class Ui {
    /** Welcome message. */
    public static final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";

    /** Goodbye message. */
    public static final String GOODBYE_MSG = "Bye. See you soon!";

    static final String LINE = "_________________________________________________________________"
            + "_______________________________________________________________________\n";
    static final String LIST_MSG = "Here are the tasks in your list:\n";
    static final String ADD_MSG = "Gotcha. I've added this task:\n";
    static final String NUMTASK_MSG = "Your current task count: ";
    static final String DONE_MSG = "I have marked this task as done:\n";
    static final String DELETE_MSG = "I have deleted this task:\n";
    static final String FIND_MSG = "Here are what i found:\n";

    private Scanner sc;

    /**
     * * Constructor for Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints text with a horizontal line on top and below it.
     *
     * @param text The text to be printed.
     */
    public static void printBlock(String text) {
        System.out.printf("%s%s\n%s", LINE, text, LINE);
    }

    /**
     * Reads in a single command.
     *
     * @return The command read.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
