package iris.ui;

import java.util.ArrayList;

import iris.exception.NoSuchTaskException;
import iris.task.Task;
import iris.task.TaskList;

/**
 * Ui class handles all user interactions of Iris.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class Ui {

    /**
     * Logo of Iris
     */
    private static final String GREETING = "Hello I'm\n"
            + "  ==      ==------\\            ==        /----------\\\n"
            + "||    ||  !    !-----\\    \\      ||    ||   /    /----------/\n"
            + "||    ||  !    !         >    >   ||    ||    \\   \\\n"
            + "||    ||  !    !-----/    /      ||    ||          \\   \\\n"
            + "||    ||  !    !----\\    \\       ||    ||               \\   \\\n"
            + "||    ||  !    !        \\    \\     ||    ||    //----------/    /\n"
            + "  ==     ==           ===     ==        \\------------/";

    /**
     * Message to greet user for GUI.
     *
     * @return String representation of Iris's greeting.
     */
    public String guiGreet() {
        System.out.println(GREETING);
        return GREETING + "\n" + "What can I do for you?";
    }

    /**
     * Message to notify users that a task has been
     * added successfully into the task list.
     *
     * @param task Task to be added into task list.
     * @param taskLeft Total number of tasks in the task list.
     * @return String representation when Iris successfully adds a task.
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
     * @return String representation when Iris successfully deletes a task.
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
     * @return String representation when Iris successfully marks a task as done.
     */
    public String guiTaskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.getStatus();
    }

    /**
     * Message to notify users that a task has been
     * marked as undone in the task list.
     *
     * @param task Task to be marked as undone.
     * @return String representation when Iris successfully marks a task as undone.
     */
    public String guiTaskUndoneMessage(Task task) {
        return "Nice! I've marked this task as undone:\n" + task.getStatus();
    }

    /**
     * Message to list out all the tasks in the task list.
     *
     * @param taskList The task list to be printed.
     * @return String representation of the list of tasks.
     * @throws NoSuchTaskException when task does not exist.
     */
    public String guiListTaskMessage(TaskList taskList) throws NoSuchTaskException {
        if (taskList.size() == 0) {
            return "There is currently no task at hand.";
        }
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                msg.append(i).append(".").append(taskList.getTask(i).getStatus()).append("\n");
            } catch (NoSuchTaskException e) {
                throw new NoSuchTaskException();
            }
        }
        return msg.toString() + "\n";
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
     * Returns string representation of all error messages.
     *
     * @param message Error message to be printed.
     * @return String representation of error message.
     */
    public static String formatErrorMessage(String message) {
        return "OOPS!!! " + message + "\n";
    }

    /**
     * Returns string representation of task related to the keyword input by user.
     *
     * @param taskList List of related tasks.
     * @return String representation of related tasks.
     */
    public String getFindTask(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "None of the tasks matched the keyword provided.";
        }
        StringBuilder relatedTaskMessage = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            relatedTaskMessage.append("\n").append(i)
                    .append(". ").append(taskList.get(i - 1).getStatus());
        }
        System.out.println(relatedTaskMessage.toString() + "\n");
        return relatedTaskMessage.toString();
    }

    /**
     * Message to notify user that previous Command
     * has been undone.
     *
     * @return String representation of undo command message.
     */
    public String guiUndoMessage() {
        return "Your most recent command has been reverted!!!\n";
    }

    public String guiHelpMessage() {
        return "Here are some of the possible inputs:\n"
                + "1. todo - create a todo task\n"
                + "2. deadline - create a deadline task\n"
                + "3. event - create an event task\n"
                + "4. done - mark an existing task as done\n"
                + "5. deletes - delete an existing task\n"
                + "6. list - list all stored tasks\n"
                + "7. find - find task from list\n"
                + "8. undo - undo previous command\n"
                + "9. bye - close program\n\n";
    }
}
