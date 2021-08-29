package ui;

import java.util.Scanner;

/**
 * The Ui Class is responsible for interactions with
 * the user directly and displays various dialog prompts
 * to help user with input.
 */
public final class Ui{
    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String SEPARATOR = "     _______________________________________________________";

    private final Scanner sc;

    /**
     * Constructs an Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the message when program is first started.
     */
    public static void welcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(SEPARATOR);
        System.out.println("     Hi! I am Duke!\n" + "     What can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Detects the next line of input by user.
     *
     * @return the detected line of input
     */
    public String readLine() {
        String s = " ";
        if (sc.hasNext()) {
          s = sc.nextLine();
        }
        return s;
    }


    /**
     * Displays certain dialogs as feedback to user.
     *
     * @param s the words to be displayed
     */
    public static void showInput(String ... s) {
        System.out.println(SEPARATOR);
        for (String str : s) {
            System.out.println("     " + str);
        }
        System.out.println(SEPARATOR);
    }


    /**
     * Displays the user's input as confirmation that
     * the input was indeed detected.
     *
     * @param s the words to be displayed.
     */
    public static void showAsUserInput(String s) {
        System.out.println(s);
    }

    /**
     * Displays the message upon termination of program.
     */
    public static void showExitMessage() {
        showInput("Bye. Hope to see you again soon!");
    }

    /**
     * Displays commands to help user with input as much as possible
     */
    public static void helperMessage() {
        System.out.println(SEPARATOR);
        System.out.println("     The following can be used:");
        System.out.println("     Types of tasks: 'todo', 'deadline', 'event'");
        System.out.println("     If you wish to add a task," +
                " please input in the form: '<Type of tasks.Task> <Name of tasks.Task>'" +
                " and include keyword '/at' OR '/by' followed by <Date> if relevant.");
        System.out.println("     If you wish to delete a task, "
                + "please input in the form: 'delete <task index>'.");
        System.out.println("     If you wish to see the current tasks, please input 'list'.");
        System.out.println("     If you wish to mark a task as done, please input 'done <task index>.'");
        System.out.println("     If you wish to terminate the program, please input 'bye'.");
        System.out.println("     If you wish to check items due on a particular day, please " +
                "input 'due YYYY/MM/DD'.");
        System.out.println("     If you wish to find items, please " +
                "input in the form: 'find <keyword>'.");
        System.out.println(SEPARATOR);
    }
}
