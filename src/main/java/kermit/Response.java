package kermit;

import kermit.tasks.Task;

/**
 * Response handles interface with user such as receiving user input and printing messages to user.
 */
public class Response {
    private static final String listText = "Here are the tasks in your list:";
    private static final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete:";
    private static final String filteredTaskText = "Here are the matching tasks in your list:";
    private static final String goodbyeText = "Bye. Hope to see you again soon!";
    private static final String errorText = "Burp burp! Something went wrong!";
    private static final String loadingErrorText = "Could not read this file Nuuuuuuu!";
    private static final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";

    /**
     * Constructs Response.
     */
    public Response() {
    }

    /**
     * Returns message to exit chat.
     *
     * @return String to exit chat.
     */
    public String getGoodbyeMessage() {
        return goodbyeText;
    }

    /**
     * Returns message of list of items in task list.
     * Message includes their statuses, completion status and additional information.
     *
     * @return Items in task list.
     */
    public String getListItems(TaskList list) {
        return listText + "\n" + list.toString();
    }

    /**
     * Returns message when task is added.
     *
     * @param task Task that is added to list.
     * @param list List that task was added to.
     * @return Message to send user when task is added.
     */
    public String getAddTaskMessage(Task task, TaskList list) {
        return "Got it. I've added this task:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Returns message when task is deleted.
     *
     * @param task Task that is deleted from list.
     * @param list List that task was deleted from.
     * @return Message to send user when task is deleted.
     */
    public String getDeleteTaskMessage(Task task, TaskList list) {
        return "Noted. I've removed this task:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Returns message when task is completed.
     *
     * @param task Task that is completed.
     * @return Message to send user when task is added.
     */
    public String getCompleteTaskMessage(Task task) {
        return completeTaskText + "\n" + task;
    }

    /**
     * Returns error message.
     *
     * @param e Error message.
     * @return Error message.
     */
    public String getErrorMessage(String e) {
        return errorText + "\n" + e;
    }

    /**
     * Returns error message when loading save file.
     *
     * @return Error message.
     */
    public String getLoadingError() {
        return loadingErrorText;
    }

    /**
     * Returns message for tasks that match filter.
     *
     * @return String of tasks that match filter
     */
    public String getFilteredTasks(TaskList filteredTasks) {
        return filteredTaskText + "\n" + filteredTasks;
    }

}
