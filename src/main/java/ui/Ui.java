package ui;

import java.util.Scanner;

/**
 * The Ui Class is responsible for interactions with
 * the user directly and displays various dialog prompts
 * to help user with input.
 */
public final class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String SEPARATOR = "     _____________________________________________"
            + "__________________________________________________________________________"
            + "____________________________________________________________";

    private final Scanner sc;

    private static final String WELCOME_MESSAGE =
            "Hello from\n" + LOGO + "\n" + SEPARATOR + "\n"
            + "     Hi! I am Duke!\n" + "     What can I do for you?\n"
            + SEPARATOR;


    private static final String HELPER_MESSAGE = SEPARATOR + "\n"
            + "     The following can be used:\n" + "     Types of tasks: 'todo', 'deadline', 'event'\n"
            + "     If you wish to add a task, please input in the form: '<Type of Task> <Name of Task>'"
            + " and include keyword '/at' OR '/by' followed by <Date> if relevant.\n"
            + "     If you wish to delete a task, please input in the form: 'delete <task index>'.\n"
            + "     If you wish to see the current tasks, please input 'list'.\n"
            + "     If you wish to mark a task as done, please input 'done <task index>.'\n"
            + "     If you wish to terminate the program, please input 'bye'.\n"
            + "     If you wish to check items due on a particular day, please input 'DD/MM/YYYY'.\n"
            + "     If you wish to find items, please input in the form: 'find <keyword>'.\n"
            + "     If you wish to reschedule a task, please input 'reschedule <index> <new date> \n"
            + "     Please kindly avoid unnecessary spaces and note that date is best in 'DD/MM/YYYY' form.\n"
            + SEPARATOR;

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Constructs an Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the welcome message upon start of application.
     * @return the welcome message to be shown to user
     */
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns the standard line separator in Duke.
     * @return the line separator
     */
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
     * Displays commands to help user with input as much as possible
     */
    public static void helperMessage() {
        System.out.println(HELPER_MESSAGE);
    }

    /**
     * Returns the helper message to be shown to the user.
     * @return message consisting of commands that can be used
     */
    public static String getHelperMessage() {
        return HELPER_MESSAGE;
    }

    /**
     * Returns the exit message upon termination of application.
     * @return the exit message to be shown to user
     */
    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }
}
