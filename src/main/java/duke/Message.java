package duke;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents messages made by Duke
 * in response to various user inputs.
 *
 * @author botr99
 */
public class Message {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke.\n" + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String INVALID_COMMAND_MESSAGE =
            "Oops!!! I'm sorry, but I don't know what that means.";
    private static final String TRY_AGAIN_MESSAGE = "Please try again or restart the application.";

    /**
     * Gets the welcome message when Duke is started,
     * just before user inputs his/her first command.
     *
     * @return A string containing the message.
     */
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Gets the exit message when user inputs "bye".
     *
     * @return A string containing the message.
     */
    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }

    private static String getTaskCountMessage(int taskCount) {
        return "Now you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Gets the task that was added and the updated number of tasks in the task list.
     *
     * @param task The task that was added.
     * @param taskCount The updated number of tasks in the task list.
     * @return A string containing the message.
     */
    public static String getAddTaskMessage(Task task, int taskCount) {
        return "Got it. I've added this task:\n" + task
                + "\n" + getTaskCountMessage(taskCount);
    }

    /**
     * Gets the user's task list.
     *
     * @param taskList The user's task list.
     * @return A string containing the message.
     */
    public static String getTaskListMessage(TaskList taskList) {
        return "Here are the tasks in your list:\n"
                + taskList;
    }

    /**
     * Gets the task that was marked as done.
     *
     * @param task The task that was marked as done.
     * @return A string containing the message.
     */
    public static String getMarkTaskAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Gets the task that was deleted and the updated number of tasks in the task list.
     *
     * @param task The task that was deleted.
     * @param taskCount The updated number of tasks in the task list.
     * @return A string containing the message.
     */
    public static String getDeleteTaskMessage(Task task, int taskCount) {
        return "Noted. I've removed this task:\n" + task
                + "\n" + getTaskCountMessage(taskCount);
    }

    /**
     * Gets the matching tasks in the user's list after the user has made a query
     * to find tasks.
     *
     * @param filteredTaskList Task list consisting of tasks that have been filtered out.
     * @return A string containing the message.
     */
    public static String getFilteredTaskListMessage(TaskList filteredTaskList) {
        return "Here are the matching tasks in your list:\n"
                + filteredTaskList;
    }

    /**
     * Gets the upcoming tasks in the user's list to be done.
     *
     * @param upcomingTaskList Task List consisting of upcoming tasks.
     * @return A string containing the message.
     */
    public static String getUpcomingTaskListMessage(TaskList upcomingTaskList) {
        return "Here are the tasks to be done before they are due:\n"
                + upcomingTaskList;
    }

    /**
     * Gets an invalid command message when the user inputs an invalid command.
     *
     * @return A string containing the message.
     */
    public static String getInvalidCommandMessage() {
        return INVALID_COMMAND_MESSAGE;
    }

    /**
     * Gets the message of the DukeException.
     *
     * @param e The DukeException thrown when Duke is being run.
     * @return A string containing the message.
     */
    public static String getDukeExceptionMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Gets the try again message when there is an IOException thrown.
     *
     * @return A string containing the message.
     */
    public static String getTryAgainMessage() {
        return TRY_AGAIN_MESSAGE;
    }

}
