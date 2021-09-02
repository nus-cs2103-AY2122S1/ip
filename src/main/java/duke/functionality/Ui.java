package duke.functionality;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Task;


/**
 * Creates an Ui system that is responsible for interacting with the user.
 */
public class Ui {
    private static final String BORDER = "________________________________________________________";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLACEHOLDER = "%text%";
    private static final String DONE_TASK = "Nice! I've marked this task as done:";
    private static final String MAX_TASK = "Sorry! You have max number of tasks stored already.";
    private static final String ADDED_TASK = "Got it. I've added this task:";
    private static final String REMAINING_TASK_NUM = String.format("Now you have %s tasks in the list.", PLACEHOLDER);
    private static final String DELETED_TASK = "Noted. I've removed this task:";
    private static final String MISSING_TASK = "There is no task at the specified index.";
    private static final String NO_MATCHING_TASKS = "There are no matching tasks found.";
    private static final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String GOODBYE_MSG = "It has been a pleasure, goodbye!";


    public Ui() {
    }

    private String prettyPrint(String msg) {
        String indentedMsg = msg.replaceAll("(?m)^", "    ");
        return String.format("%s\n%s\n%s%n", BORDER, indentedMsg, BORDER);
    }

    /**
     * Sends a welcome message to the user.
     */
    public String welcomeMessage() {
        String welcome = "Duke is gone. Hello, this is Duchess.\nHow can I help you?";
        return String.format("%s\n%s\n%s%n", BORDER, welcome, BORDER);
    }

    /**
     * Sends a goodbye message to the user.
     */
    public String goodbyeMessage() {
        return prettyPrint(GOODBYE_MSG);
    }

    /**
     * Sends a message to the user when a task is added.
     *
     * @param addedTask The task that has been added.
     * @param taskLeftNum The number of tasks present in the current list of tasks.
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
     */
    public String markedDoneMessage(Task task) {
        String msg = String.format("%s\n  %s", DONE_TASK, task.toString());
        return prettyPrint(msg);
    }

    /**
     * Prints out the list of current tasks to the user.
     *
     * @param stringifyTaskLast String representation of current list of tasks.
     */
    public String printTaskListMessage(String stringifyTaskLast) {
        return prettyPrint(stringifyTaskLast);
    }

    /**
     * Prints out the list of matching tasks to the user.
     *
     * @param listOfMatches List of all tasks that match the user's keyword.
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
     * Sends a message to the user when an unknown command has been input by them.
     */
    public String unknownCommandMessage() {
        return prettyPrint(UNKNOWN_COMMAND);
    }

    /**
     * Sends a message to the user when an error/exception has been encountered.
     *
     * @param errorMsg The error message.
     */
    public static String showErrorMessage(String errorMsg) {
        return errorMsg;
    }

    public static String checkExitMessage() {
        Ui temp = new Ui();
        return temp.goodbyeMessage();
    }

    /**
     * Sends a message to the user when the task indicated by them cannot be found.
     */
    public String missingTaskMessage() {
        return prettyPrint(MISSING_TASK);
    }

    /**
     * Sends a message to the user when no more tasks can be added to the task list.
     */
    public String maxTaskReachedMessage() {
        return prettyPrint(MAX_TASK);
    }

}
