package duke.ui;

import java.util.ArrayList;

import duke.exception.NoSuchTaskException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui class handles all user interactions of Duke.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class Ui {

    /**
     * Logo of Duke
     */
    private static final String GREETING = "Hello I'm\n"
            + " ___           _        \n"
            + "|  __ \\ _   _ | | _____\n"
            + "| |   | |  | |  | |/ /  _  \\\n"
            + "| |__| |  |_|  |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Message to greet user for GUI.
     *
     * @return String representation of Duke's greeting.
     */
    public String guiGreet() {
        return GREETING + "\n" + "What can I do for you?";
    }

    /**
     * Message to notify users that a task has been
     * added successfully into the task list.
     *
     * @param task Task to be added into task list.
     * @param taskLeft Total number of tasks in the task list.
     * @return String representation when Duke successfully adds a task.
     */
    public String guiTaskAddedMessage(Task task, int taskLeft) {
        String msg = "Got it. I've added this task:\n" + task.getStatus();
        msg += "\n" + "Now you have " + taskLeft + " tasks in the list.";
        return msg;
    }

    /**
     * Message to notify users that a task has been
     * removed successfully from the task list.
     *
     * @param task Task to be removed from task list.
     * @param taskLeft Total number of tasks left in the task list.
     * @return String representation when Duke successfully deletes a task.
     */
    public String guiTaskDeletedMessage(Task task, int taskLeft) {
        String msg = "Noted. I've removed this task:\n" + task.getStatus();
        msg += "\n" + "Now you have " + taskLeft + " tasks in the list.";
        return msg;
    }

    /**
     * Message to notify users that a task has been
     * marked as done in the task list.
     *
     * @param task Task to be marked as done.
     * @return String representation when Duke successfully marks a task as done.
     */
    public String guiTaskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.getStatus();
    }

    /**
     * Message to list out all the tasks in the task list.
     *
     * @param taskList The task list to be printed.
     * @return String representation of the list of tasks.
     */
    public String guiListTaskMessage(TaskList taskList) throws NoSuchTaskException {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                msg.append(i).append(".").append(taskList.getTask(i).getStatus()).append("\n");
            } catch (NoSuchTaskException e) {
                throw new NoSuchTaskException(this);
            }
        }
        return msg.toString();
    }

    /**
     * Message to say good bye to user.
     *
     * @return String representation of farewell message.
     */
    public String guiBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all error messages in a certain format.
     *
     * @param message Error message to be printed.
     * @return String representation of error message.
     */
    public static String formatErrorMessage(String message) {
        return "OOPS!!! " + message + "\n";
    }

    /**
     * Prints the list of task related to the keyword input by user.
     *
     * @param taskList List of related tasks.
     * @return String representation of related tasks.
     */
    public String getFindTask(ArrayList<Task> taskList) {
        StringBuilder relatedTaskMessage = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 1; i < taskList.size(); i++) {
            relatedTaskMessage.append("\n").append(i)
                    .append(". ").append(taskList.get(i - 1).getStatus());
        }
        return relatedTaskMessage.toString();
    }
}
