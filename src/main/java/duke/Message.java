package duke;

import duke.task.Task;

/**
 * Represents messages made by Duke
 * in response to various user inputs.
 *
 * @author botr99
 */
public class Message {

    /**
     * Gets the welcome message when Duke is started,
     * just before user inputs his/her first command.
     *
     * @return A string containing the message.
     */
    public static String getWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return logo + "Hello! I'm Duke.\n" + "What can I do for you?";
    }

    /**
     * Gets the exit message when user inputs "bye".
     *
     * @return A string containing the message.
     */
    public static String getExitMessage() {
        return "Bye. Hope to see you again soon!";
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
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Gets the user's task list.
     *
     * @param tasks The user's task list.
     * @return A string containing the message.
     */
    public static String getTasksMessage(TaskList tasks) {
        return "Here are the tasks in your list:\n"
                + tasks.toString();
    }

    /**
     * Gets the task that was marked as done.
     *
     * @param task The task that was marked as done.
     * @return A string containing the message.
     */
    public static String getMarkTaskDoneMessage(Task task) {
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
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Gets the matching tasks in the user's list after the user has made a query
     * to find tasks.
     *
     * @param tasks Tasks that have been filtered out.
     * @return A string containing the message.
     */
    public static String getFindTasksMessage(TaskList tasks) {
        return "Here are the matching tasks in your list:\n"
                + tasks.toString();
    }

    /**
     * Gets an invalid command message when the user inputs an invalid command.
     *
     * @return A string containing the message.
     */
    public static String getInvalidCommandMessage() {
        return "Oops!!! I'm sorry, but I don't know what that means.";
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
        return "Please try again or restart the application.";
    }

}
