package myjournal;

import myjournal.task.Task;

/**
 * Creates the Ui object.
 *
 * @author felissafaustine
 */
public class Ui {
    /**
     * Prints the welcome message.
     *
     * @return The welcome message.
     */
    public static String welcomeMessage() {
        return "Hello!\n"
                + "1. Type a task to be added into your task list, in form of: \n"
                + "   - todo [description]\n"
                + "   - event [description] /at [time]\n"
                + "   - deadline [description /by [time]\n"
                + "([time] is in form of yyyy-mm-dd for date and hh:mm for time)\n"
                + "2. Type 'list' if you want to generate your task list.\n"
                + "3. Type 'done [task number]' to mark a task as done.\n"
                + "4. Type 'delete [task number]' to delete a task.\n"
                + "5. Type 'find [keyword]' to get the list of tasks with that keyword.\n"
                + "6. Type 'edit [task number] time [the edited time]' to edit the time of the task.\n"
                + "7. Type 'edit [task number] description [the edited description]' to edit the description"
                + "   of the task.\n"
                + "8. Type 'bye' to exit";
    }

    /**
     * Prints out the statement after a task is added.
     *
     * @param taskList The list of all tasks.
     * @return The task added.
     */
    public String taskAddPrint(TaskList taskList) {
        assert taskList != null : "TaskList should not be null";
        return "Okay!! I've added the following task:\n"
                + taskList.getTask(taskList.getSize() - 1) + "\n"
                + "Now you have " + taskList.getSize() + (taskList.getSize() == 1 ? " task" : " tasks")
                + " in the list";
    }

    /**
     * Prints the task that has been deleted.
     *
     * @param task The task that has been deleted.
     * @return The task removed.
     */
    public String removeTaskPrint(Task task) {
        assert task != null : "Task should not be null";
        return "Okay!! I have removed the following task:\n"
                + task;
    }

    /**
     * Prints the task that has been edited.
     *
     * @param task The task that has been edited.
     * @return The task edited.
     */
    public String editTaskPrint(Task task) {
        assert task != null : "Task should not be null";
        return "Okay!! I have edited the following task:\n"
                + task;
    }

    /**
     * Prints the task with the keyword.
     *
     * @param tasks The list of the tasks with the keyword.
     * @return The tasks found.
     */
    public String findTaskPrint(TaskList tasks) {
        assert tasks != null : "TaskList should not be null";
        if (tasks.getSize() == 0) {
            return "You have no task that matches the word!";
        } else {
            String s = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                s = s + (i + 1) + "." + tasks.getTask(i) + "\n";
            }
            return s;
        }
    }

    /**
     * Prints the task that has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return The task marked as done.
     */
    public String doneTaskPrint(Task task) {
        assert task != null : "Task should not be null";
        return "Okay!! I have marked this task as done:\n" + task;
    }

    /**
     * Prints the goodbye message.
     *
     * @return The goodbye message.
     */
    public static String goodByeMessage() {
        return "Bye. Hope to see you again soon!:)";
    }
}
