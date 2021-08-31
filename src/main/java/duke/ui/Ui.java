package duke.ui;

import duke.TaskList;
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
    private static final String NO_TASKS_MESSAGE = "No tasks to display.";

    /**
     * Gets Duke's welcome message.
     *
     * @return Returns welcome message.
     */
    public static String welcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Gets Duke's goodbye message.
     *
     * @return Returns goodbye message.
     */
    public static String goodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Gets user's list of tasks.
     *
     * @param list Task list to be displayed.
     * @param size Size of the task list.
     * @return Returns formatted list of tasks.
     */
    public static String displayTasks(TaskList list, int size) {
        return size == 0 ? NO_TASKS_MESSAGE : LIST_MESSAGE + list.toString();
    }

    /**
     * Gets a message when a task is added.
     *
     * @param task Task added.
     * @param size Number of tasks in the user's task list.
     * @return Returns message containing acknowledgement and task added.
     */
    public static String addTaskMessage(Task task, int size) {
        return String.format(ADD_MESSAGE, task, size, size == 1 ? "task" : "tasks");
    }

    /**
     * Gets a message when a task is deleted.
     *
     * @param task Task deleted.
     * @param size Number of tasks in the user's task list.
     * @return Returns message containing acknowledgement and task deleted.
     */
    public static String deleteMessage(Task task, int size) {
        return String.format(REMOVE_MESSAGE, task, size, size == 1 ? "task" : "tasks");
    }

    /**
     * Gets a message when a task is marked as done.
     *
     * @param task Task marked as done.
     * @return Returns message containing acknowledgement and task marked as done.
     */
    public static String doneMessage(Task task) {
        return DONE_MESSAGE + task;
    }
}
