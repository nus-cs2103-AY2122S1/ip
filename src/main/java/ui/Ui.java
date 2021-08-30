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
    private final static String SEPARATOR = "     _____________________________________________" +
            "__________________________________________________________________________" +
            "____________________________________________________________";

    private final Scanner sc;

    private final static String WELCOME_MESSAGE =
            "Hello from\n" + LOGO + "\n" + SEPARATOR + "\n"
            + "     Hi! I am Duke!\n" + "     What can I do for you?\n"
            + SEPARATOR;


    private final static String HELPER_MESSAGE = SEPARATOR + "\n"
            + "     The following can be used:\n" + "     Types of tasks: 'todo', 'deadline', 'event'\n"
            + "     If you wish to add a task, please input in the form: '<Type of tasks.Task> <Name of tasks.Task>'"
            + " and include keyword '/at' OR '/by' followed by <Date> if relevant.\n"
            + "     If you wish to delete a task, please input in the form: 'delete <task index>'.\n"
            + "     If you wish to see the current tasks, please input 'list'.\n"
            + "     If you wish to mark a task as done, please input 'done <task index>.'\n"
            + "     If you wish to terminate the program, please input 'bye'.\n"
            + "     If you wish to check items due on a particular day, please input 'due YYYY/MM/DD'.\n"
            + "     If you wish to find items, please input in the form: 'find <keyword>'.\n"
            + SEPARATOR;
    private final static String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

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
        System.out.println(WELCOME_MESSAGE);
    }

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
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

    public String getSeparator() {
        return SEPARATOR;
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
        showInput(EXIT_MESSAGE);
    }

    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Displays commands to help user with input as much as possible
     */
    public static void helperMessage() {
        System.out.println(HELPER_MESSAGE);
    }

    public static String getHelperMessage() {
        return HELPER_MESSAGE;
    }
}
