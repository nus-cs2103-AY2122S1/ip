package duke;

import duke.task.Task;

/**
 * This class encapsulates Duke's methods for printing information to the user.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n%s\nNow you have %d %s in your list.";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String REMOVE_MESSAGE = "Noted. I've removed this task:\n%s\nNow you have %d %s in your list.";
    private static final String LINE = "____________________________________________________________\n";

    /**
     * Prints Duke's welcome message.
     */
    public static void welcomeMessage() {
        formatAndPrint(WELCOME_MESSAGE);
    }

    /**
     * Prints Duke's goodbye message.
     */
    public static void goodbyeMessage() {
        formatAndPrint(GOODBYE_MESSAGE);
    }

    /**
     * Displays the user's task list.
     *
     * @param list User's task list.
     */
    public static void displayTasks(TaskList list) {
        formatAndPrint(LIST_MESSAGE + list.toString());
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task Task added.
     * @param size Number of tasks in the user's task list.
     */
    public static void addTaskMessage(Task task, int size) {
        formatAndPrint(String.format(ADD_MESSAGE, task, size, size == 1 ? "task" : "tasks"));
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task Task deleted.
     * @param size Number of tasks in the user's task list.
     */
    public static void deleteMessage(Task task, int size) {
        formatAndPrint(String.format(REMOVE_MESSAGE, task, size, size == 1 ? "task" : "tasks"));
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task Task marked as done.
     */
    public static void doneMessage(Task task) {
        formatAndPrint(DONE_MESSAGE + task);
    }

    /**
     * Helper function to format output between 2 lines.
     *
     * @param output String to be outputted.
     */
    public static void formatAndPrint(String output) {
        System.out.printf("%s%s\n%s", LINE, output, LINE);
    }
}
