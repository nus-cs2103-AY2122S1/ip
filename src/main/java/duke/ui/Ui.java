package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Ui class handles all user interactions of Duke.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    /**
     * Logo of Duke
     */
    private static final String GREETING = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Message to greet the user.
     */
    public void greet() {
        System.out.println(GREETING);
        printWithFormat("Hello! I'm Duke\n" + INDENTATION + "What can I do for you?");
    }

    /**
     * Message to notify users that a task has been
     * added successfully into the task list.
     *
     * @param task Task to be added into task list.
     * @param taskLeft Total number of tasks in the task list.
     */
    public void taskAddedMessage(Task task, int taskLeft) {
        String msg = "Got it. I've added this task:\n" + INDENTATION + task.getStatus();
        msg += "\n" + INDENTATION + "Now you have " + taskLeft + " tasks in the list.";
        printWithFormat(msg);
    }

    /**
     * Message to notify users that a task has been
     * removed successfully from the task list.
     *
     * @param task Task to be removed from task list.
     * @param taskLeft Total number of tasks left in the task list.
     */
    public void taskDeletedMessage(Task task, int taskLeft) {
        String msg = "Noted. I've removed this task:\n" + INDENTATION + task.getStatus();
        msg += "\n" + INDENTATION + "Now you have " + taskLeft + " tasks in the list.";
        printWithFormat(msg);
    }

    /**
     * Message to notify users that a task has been
     * makred as done in the task list.
     *
     * @param task Task to be marked as done.
     */
    public void taskDoneMessage(Task task) {
        String msg = "Nice! I've marked this task as done:\n" + INDENTATION + task.getStatus();
        printWithFormat(msg);
    }

    /**
     * Message to list out all the tasks in the task list.
     *
     * @param taskList The task list to be printed.
     */
    public void listTaskMessage(TaskList taskList) {
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            msg += INDENTATION + + i + "." + taskList.getTask(i).getStatus() + "\n";
        }
        printWithFormat(msg);
    }

    /**
     * Message to say good bye to user.
     */
    public void bye() {
        printWithFormat("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all messages in a certain format.
     *
     * @param message Message to be printed.
     */
    public static void printWithFormat(String message) {
        System.out.println(LINE);
        System.out.println(INDENTATION + message);
        System.out.println(LINE);
    }

    /**
     * Prints all error messages in a certain format.
     *
     * @param message Error message to be printed.
     * @return String representation of error message.
     */
    public static String formatErrorMessage(String message) {
        String errorMessage = LINE + "\n";
        errorMessage += INDENTATION + "☹ OOPS!!! " + message + "\n";
        errorMessage += LINE;
        return errorMessage;
    }

    /**
     * Prints the list of task related to the keyword input by user.
     *
     * @param taskList List of related tasks.
     */
    public void printFindTask(ArrayList<Task> taskList) {
        String relatedTaskMessage = "Here are the matching tasks in your list:";
        for (int i = 1; i < taskList.size(); i++) {
            relatedTaskMessage += "\n" + INDENTATION + i + ". " + taskList.get(i - 1).getStatus();
        }
        printWithFormat(relatedTaskMessage);
    }
}
