package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * A class in charge of the interaction to {@code Duke} users.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String LOGO = "\t____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING_TEXT = "\tHello from \n"
            + LOGO
            + "\tHow can I help you?";

    private static final String FAREWELL_TEXT = "\t☹☹☹ Why do you choose to leave me!";

    /**
     * Read the input from the user.
     */
    public static String readCommand() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        return scanner.next();
    }

    /**
     * Print the message between two horizontal line.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println("\t" + HORIZONTAL_LINE + "\n" + message + "\n\t" + HORIZONTAL_LINE);
    }

    /**
     * Greet the users by printing the greeting text.
     */
    public static void greet() {
        printMessage(GREETING_TEXT);
    }

    /**
     * Farewell the users by printing the farewell message.
     */
    public static void farewell() {
        printMessage(FAREWELL_TEXT);
    }

    /**
     * Prints a message when a task is added to a {@code TaskList}.
     *
     * @param tasks The list to which a new task is added.
     * @param task  The task added to the list.
     */
    public static void addTaskMessage(TaskList tasks, Task task) {
        String message = "\tGot it. I've added this task:\n\t\t"
                + task
                + "\n\tTask(s) remaining in the list: "
                + tasks.size();
        printMessage(message);
    }

    /**
     * Prints a message <strong>after</strong> a task is removed from the list.
     *
     * @param tasks The list from which a task is removed.
     * @param task  The task that is removed.
     */
    public static void removeTaskMessage(TaskList tasks, Task task) {
        int tasksSize = tasks.size();
        String message = "\tNoted. I've removed this task:\n\t\t"
                + task
                + "\n\tTask(s) remaining in the list: "
                + tasksSize;
        printMessage(message);
    }

    /**
     * Prints a message when a task is done.
     *
     * @param task The task list to be marked done.
     */
    public static void taskDoneMessage(Task task) {
        String message = "\tNice! I've marked this task as done:\n\t\t" + task;
        printMessage(message);
    }

    /**
     * Prints the error message.
     *
     * @param error The error message.
     */
    public static void reportError(String error) {
        printMessage("\t" + error);
    }

    /**
     * Prints the error message.
     *
     * @param e The exception.
     */
    public static void reportError(Exception e) {
        printMessage("\t" + e.getMessage());
    }
}
