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
     * @return
     */
    public static String welcomeMessage() {
        return "Hello!\n"
                + "1. Type a task (todo/event/deadline) to be added into your task list,\n"
                + "followed by the time for events and due date for deadlines \n"
                + "in form of yyyy-mm-dd for date and hh:mm for time.\n"
                + "2. Type 'list' if you want to generate your task list.\n"
                + "3. Type 'done [number]' to mark a task as done.\n"
                + "4. Type 'delete [number]' to delete a task.\n"
                + "5. Type 'find [keyword]' to get the list of tasks with that keyword.\n"
                + "6. Type 'bye' to exit";
    }

    /**
     * Prints out the statement after a task is added.
     *
     * @param taskList The list of all tasks.
     */
    public String taskAddPrint(TaskList taskList) {
        return "Okay!! I've added the following task:\n"
                + taskList.getTask(taskList.getSize() - 1) + "\n"
                + "Now you have " + taskList.getSize() + " in the list";
    }

    /**
     * Prints the task that has been deleted.
     *
     * @param task The task that has been deleted.
     */
    public String removeTaskPrint(Task task) {
        return "Okay!! I have removed the following task:\n"
                + task;
    }

    /**
     * Prints the task with the keyword.
     *
     * @param tasks The list of the tasks with the keyword.
     */
    public String findTaskPrint(TaskList tasks) {
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
     */
    public String doneTaskPrint(Task task) {
        return "Okay!! I have marked this task as done:\n" + task;
    }

    /**
     * Prints the goodbye message.
     */
    public static String goodByeMessage() {
        return "Bye. Hope to see you again soon!:)";
    }
}
