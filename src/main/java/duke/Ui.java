package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * A class in charge of the interaction to {@code Duke} users.
 */
public class Ui {
    private static final String LOGO = "____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING_TEXT = "Hello from \n"
            + LOGO
            + "How can I help you?";

    private static final String FAREWELL_TEXT = "☹☹☹ Why do you choose to leave me!";

    /**
     * Read the input from the user.
     */
    public static String readCommand() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        return scanner.next();
    }

    /**
     * Greet the users by printing the greeting text.
     */
    public static String greet() {
        return GREETING_TEXT;
    }

    /**
     * Farewell the users by printing the farewell message.
     */
    public static String farewell() {
        return FAREWELL_TEXT;
    }

    /**
     * Prints a message when a task is added to a {@code TaskList}.
     *
     * @param tasks The list to which a new task is added.
     * @param task  The task added to the list.
     */
    public static void addTaskMessage(TaskList tasks, Task task) {
        String message = "Got it. I've added this task:\n\t"
                + task
                + "\nTask(s) remaining in the list: "
                + tasks.size();
        //        getMessage(message);
    }

    /**
     * Prints a message <strong>after</strong> a task is removed from the list.
     *
     * @param tasks The list from which a task is removed.
     * @param task  The task that is removed.
     */
    public static void removeTaskMessage(TaskList tasks, Task task) {
        int tasksSize = tasks.size();
        String message = "Noted. I've removed this task:\n\t"
                + task
                + "\nTask(s) remaining in the list: "
                + tasksSize;
        // getMessage(message);
    }

    /**
     * Prints a message when a task is done.
     *
     * @param task The task list to be marked done.
     */
    public static void taskDoneMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n\t" + task;
        // getMessage(message);
    }

    /**
     * Prints the error message.
     *
     * @param error The error message.
     */
    public static void reportError(String error) {
        // getMessage("\t" + error);
    }

    /**
     * Prints the error message.
     *
     * @param e The exception.
     */
    public static void reportError(Exception e) {
        // getMessage("\t" + e.getMessage());
    }
}
