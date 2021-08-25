package duke.functionality;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Creates an Ui system that is responsible for interacting with the user.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLACEHOLDER = "%text%";
    private static final String DONE_TASK = "Nice! I've marked this task as done:";
    private static final String MAX_TASK = "Sorry! You have max number of tasks stored already.";
    private static final String ADDED_TASK = "Got it. I've added this task:";
    private static final String REMAINING_TASK_NUM = String.format("Now you have %s tasks in the list.", PLACEHOLDER);
    private static final String DELETED_TASK = "Noted. I've removed this task:";
    private static final String MISSING_TASK = "There is no task at the specified index.";
    private static final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";


    private final Scanner sc;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    private Ui(InputStream in, PrintStream out) {
        this.sc = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void closeReader() {
        this.sc.close();
    }

    private void prettyPrintToUser(String msg) {
        String indentedMsg = msg.replaceAll("(?m)^", "    ");
        out.printf("%s\n%s\n%s%n", BORDER, indentedMsg, BORDER);
    }

    /**
     * Sends a welcome message to the user.
     */
    public void welcomeMessage() {
        out.printf("Duke is gone. Hello, this is Duchess.\nHow can I help you?\n%s%n", BORDER);
    }

    /**
     * Sends a goodbye message to the user.
     */
    public void goodbyeMessage() {
        prettyPrintToUser("It has been a pleasure, goodbye!");
    }

    /**
     * Sends a message to the user when a task is added.
     *
     * @param addedTask The task that has been added.
     * @param taskLeftNum The number of tasks present in the current list of tasks.
     */
    public void taskAddedMessage(Task addedTask, int taskLeftNum) {
        String tasksLeft = REMAINING_TASK_NUM.replace(PLACEHOLDER, String.valueOf(taskLeftNum));
        String msg = String.format("%s\n  %s\n%s", ADDED_TASK, addedTask.toString(), tasksLeft);
        prettyPrintToUser(msg);
    }

    /**
     * Sends a message to the user when a task is deleted.
     *
     * @param addedTask The task that has been deleted.
     * @param taskLeftNum The number of remaining tasks present in the current list of tasks.
     */
    public void taskDeletedMessage(Task removedTask, int taskLeftNum) {
        String tasksLeft = REMAINING_TASK_NUM.replace(PLACEHOLDER, String.valueOf(taskLeftNum));
        String msg = String.format("%s\n  %s\n%s", DELETED_TASK, removedTask.toString(), tasksLeft);
        prettyPrintToUser(msg);
    }

    /**
     * Sends a message to the user when a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void markedDoneMessage(Task task) {
        String msg = String.format("%s\n  %s", DONE_TASK, task.toString());
        prettyPrintToUser(msg);
    }

    /**
     * Prints out the list of current tasks to the user.
     *
     * @param stringifyTaskLast String representation of current list of tasks.
     */
    public void printTaskListMessage(String stringifyTaskLast) {
        //change Storage to String taskListMessage later on
        prettyPrintToUser(stringifyTaskLast);
    }

    /**
     * Sends a message to the user when an unknown command has been input by them.
     */
    public void unknownCommandMessage() {
        prettyPrintToUser(UNKNOWN_COMMAND);
    }

    /**
     * Sends a message to the user when an error/exception has been encountered.
     *
     * @param errorMsg The error message.
     */
    public static void showErrorMessage(String errorMsg) {
        System.err.println(errorMsg);
    }

    /**
     * Sends a message to the user when the task indicated by them cannot be found.
     */
    public void missingTaskMessage() {
        prettyPrintToUser(MISSING_TASK);
    }

    /**
     * Sends a message to the user when no more tasks can be added to the task list.
     */
    public void maxTaskReachedMessage() {
        prettyPrintToUser(MAX_TASK);
    }

}