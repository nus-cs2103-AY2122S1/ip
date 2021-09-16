package duke.functionality;

import java.util.List;

import duke.tasks.Task;

/**
 * Creates an Ui system that is responsible for creation of responses to be output to user.
 */
public class Ui {
    private static final String PLACEHOLDER = "%text%";
    private static final String DONE_TASK = "Nice! I've marked this task as done:";
    private static final String MAX_TASKS = "Sorry! You have max number of tasks stored already.";
    private static final String MAX_ARCHIVE_TASKS = "Sorry! You have max number of archivable tasks stored already.";
    private static final String ADDED_TASK = "Got it. I've added this task:";
    private static final String REMAINING_TASK_NUM = String.format("Now you have %s tasks in the list.", PLACEHOLDER);
    private static final String DELETED_TASK = "Noted. I've removed this task:";
    private static final String MISSING_TASK = "There is no task at the specified index.";
    private static final String NO_MATCHING_TASKS = "There are no matching tasks found.";
    private static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :(";
    private static final String GOODBYE_MSG = "It has been a pleasure, goodbye!";
    private static final String TASKS_ARCHIVED = "All specified tasks have been successfully archived!";
    private static final String TASKS_RESTORED = "All specified tasks have been successfully restored!";

    /**
     * Returns an Ui object that is responsible for the creation of responses to be output to the user.
     */
    public Ui() {
    }

    private static String prettyPrint(String msg) {
        String indentedMsg = msg.replaceAll("(?m)^", "    ");
        return indentedMsg;
    }

    /**
     * Sends a welcome message to the user.
     *
     * @return Welcome message.
     */
    public String welcomeMessage() {
        String welcome = "Helwoof, this is WoofBot.\nHow can I help you?";
        return prettyPrint(welcome);
    }

    /**
     * Sends a goodbye message to the user.
     *
     * @return Goodbye message.
     */
    public String goodbyeMessage() {
        return prettyPrint(GOODBYE_MSG);
    }

    /**
     * Sends a message to the user when a task is added.
     *
     * @param addedTask The task that has been added.
     * @param taskLeftNum The number of tasks present in the current list of tasks.
     * @return Message that task has been added.
     */
    public String taskAddedMessage(Task addedTask, int taskLeftNum) {
        String tasksLeft = REMAINING_TASK_NUM.replace(PLACEHOLDER, String.valueOf(taskLeftNum));
        String msg = String.format("%s\n  %s\n%s", ADDED_TASK, addedTask.toString(), tasksLeft);
        return prettyPrint(msg);
    }

    /**
     * Sends a message to the user when a task is deleted.
     *
     * @param removedTask The task that has been deleted.
     * @param taskLeftNum The number of remaining tasks present in the current list of tasks.
     * @return Message that task has been deleted.
     */
    public String taskDeletedMessage(Task removedTask, int taskLeftNum) {
        String tasksLeft = REMAINING_TASK_NUM.replace(PLACEHOLDER, String.valueOf(taskLeftNum));
        String msg = String.format("%s\n  %s\n%s", DELETED_TASK, removedTask.toString(), tasksLeft);
        return prettyPrint(msg);
    }

    /**
     * Sends a message to the user when a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return Message that task has been marked as done.
     */
    public String markedDoneMessage(Task task) {
        String msg = String.format("%s\n  %s", DONE_TASK, task.toString());
        return prettyPrint(msg);
    }

    /**
     * Prints out the list of tasks to the user.
     *
     * @param stringifyTaskLast String representation of current list of tasks.
     * @return Message of list of tasks saved.
     */
    public String printTaskListMessage(String stringifyTaskLast) {
        return prettyPrint(stringifyTaskLast);
    }

    /**
     * Prints out the list of matching tasks to the user.
     *
     * @param listOfMatches List of all tasks that match the user's keyword.
     * @return Message of matching tasks found.
     */
    public String printMatchingTasksMessage(List<Task> listOfMatches) {
        int len = listOfMatches.size();
        if (len == 0) {
            return prettyPrint(NO_MATCHING_TASKS);
        } else {
            StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < len; i++) {
                int currTaskNum = i + 1;
                msg.append("\n").append(currTaskNum).append(". ").append(listOfMatches.get(i).toString());
            }
            return prettyPrint(msg.toString());
        }
    }

    /**
     * Sends a message to the user that all tasks specified have been archived successfully.
     *
     * @return Message that task has been archived.
     */
    public String tasksArchivedMessage() {
        return prettyPrint(TASKS_ARCHIVED);
    }

    /**
     * Sends a message to the user that all archived tasks specified have been restored successfully.
     *
     * @return Message that task has been restored.
     */
    public String tasksRestoredMessage() {
        return prettyPrint(TASKS_RESTORED);
    }

    /**
     * Sends a message to the user when an unknown command has been input by them.
     *
     * @return Message that an invalid command has been input.
     */
    public String unknownCommandMessage() {
        return UNKNOWN_COMMAND;
    }

    /**
     * Sends a message to the user when an error/exception has been encountered.
     *
     * @param errorMsg The error message.
     * @return Message about error.
     */
    public static String showErrorMessage(String errorMsg) {
        return errorMsg;
    }

    /**
     * Checks if the given string is equivalent to the exit message.
     *
     * @param inputString The string to be compared.
     * @return True if they are equivalent, false otherwise.
     */
    public static Boolean checkExitMessage(String inputString) {
        Ui temp = new Ui();
        return inputString.equals(temp.goodbyeMessage());
    }

    /**
     * Sends a message to the user when the task indicated by them cannot be found.
     *
     * @param index The task index specified by the user, which cannot be found.
     * @return Message that there is no such task available.
     */
    public String missingTaskMessage(int index) {
        String msg = String.format("%s Index: %d", MISSING_TASK, index);
        return prettyPrint(msg);
    }

    /**
     * Sends a message to the user when no more tasks can be added to the task list.
     *
     * @return Message that max number of tasks that can be saved has been reached.
     */
    public String maxTaskReachedMessage() {
        return prettyPrint(MAX_TASKS);
    }

    /**
     * Sends a message to the user when no more archivable tasks can be added to the archive task list.
     *
     * @return Message that max number of tasks that can be archived has been reached.
     */
    public String maxArchiveTaskReachedMessage() {
        return prettyPrint(MAX_ARCHIVE_TASKS);
    }

}
