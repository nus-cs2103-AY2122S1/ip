package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class UI {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String DIVIDER = "____________________________________________________________\n";

    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        showLine();
        System.out.println("Hello I'm Duke\nWhat can I do for you?\n");
        showLine();
    }

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.print(DIVIDER);
    }

    /**
     * Prints the message after executing an exit command.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints the message after executing a done command.
     *
     * @param task The task that was marked as done.
     */
    public void showDone(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
    }

    /**
     * Prints the message after executing a list command.
     */
    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints the message after executing a delete command.
     *
     * @param task The task that was deleted.
     * @param size Updated size of the list.
     */
    public void showDelete(Task task, int size) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n", task, size);
    }

    /**
     * Prints the message after executing a find command.
     */
    public void showFind() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints the message after executing an add command.
     *
     * @param task The task that was added.
     * @param size Updated size of the list.
     */
    public void showAdd(Task task, int size) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", task, size);
    }

    /**
     * Prints the error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Returns the command input by the user.
     *
     * @return command input by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
