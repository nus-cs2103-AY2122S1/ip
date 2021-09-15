package ui;

import java.util.Scanner;

/**
 * The Ui Class is responsible for interactions with
 * the user directly and displays various dialog prompts
 * to help user with input.
 */
public final class Ui {

    private final String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    private final Scanner sc;

    private final String welcomeMessage =
            "     Hello from\n" + logo + "\n" + "\n"
            + "     Hi! I am Duke!\n" + "     What can I do for you?\n";


    private final String helperMessage =
            "     The following can be used:\n" + "     Types of tasks: 'todo', 'deadline', 'event'\n"
            + "     If you wish to add a task, please input in the form:\n'"
            + "     <type of task> <description of task>'"
            + " and include keyword '/at' OR '/by' followed by <date> and <time> if relevant.\n\n"
            + "     If you wish to delete a task, please input in the form: 'delete <task index>'.\n"
            + "     If you wish to see the current tasks, please input 'list'.\n"
            + "     If you wish to mark a task as done, please input 'done <task index>.'\n"
            + "     If you wish to terminate the program, please input 'bye'.\n"
            + "     If you wish to check items due on a particular day, please input 'due <date>'.\n"
            + "     If you wish to find items, please input in the form: 'find <keyword>'.\n"
            + "     If you wish to reschedule a task, please input 'reschedule <index> <new date> \n"
            + "     Please kindly avoid unnecessary spaces and note that date and time is best in "
            + "'DD/MM/YYYY HH/MM' form.\n";

    private final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * Constructs an Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the welcome message upon start of application.
     * @return the welcome message to be shown to user
     */
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * Displays certain dialogs as feedback to user.
     *
     * @param s the words to be displayed
     */
    public void showInput(String ... s) {
        System.out.println();
        for (String str : s) {
            System.out.println("     " + str);
        }
        System.out.println();
    }

    /**
     * Displays commands to help user with input as much as possible
     */
    public void showHelperMessage() {
        System.out.println(helperMessage);
    }

    /**
     * Returns the helper message to be shown to the user.
     * @return message consisting of commands that can be used
     */
    public String getHelperMessage() {
        return helperMessage;
    }

    /**
     * Returns the exit message upon termination of application.
     * @return the exit message to be shown to user
     */
    public String getExitMessage() {
        return exitMessage;
    }
}
