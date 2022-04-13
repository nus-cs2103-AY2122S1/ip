package duke.ui;

import duke.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class TextUi {

    /**
     * Prints the message after a task is added.
     *
     * @param tasks The TaskList.
     * @return String representation of the task added.
     */
    public static String showTaskAdded(TaskList tasks) {
        return "added: " + tasks.getTaskList().get(tasks.getLength() - 1) + "\n";
    }

    /**
     * Prints the message after a task is removed.
     *
     * @param t The task.
     * @return String representaiton of the task removed.
     */
    public static String showTaskRemoved(Task t) {
        return "Noted. I've removed this task: \n" + t.toString() + "\n";
    }

    /**
     * Prints the message after a task is marked as done.
     *
     * @param t The task.
     * @return String representation of the task marked as done.
     */
    public static String showTaskDone(Task t) {
        return t.toString() + "\nThe task is marked as done! Good job :D\n";
    }

    /**
     * Prints the message after the TaskList is updated.
     *
     * @param tasks The TaskList.
     * @return String representation of the updated number of tasks.
     */
    public static String showUpdatedNumberOfTasks(TaskList tasks) {
        int len = tasks.getLength();
        if (len == 1) {
            return "Now you have " + tasks.getLength() + " task in the list.\n";
        } else {
            return "Now you have " + tasks.getLength() + " tasks in the list.\n";
        }
    }

    /**
     * Prints the error message.
     *
     * @param s The error message in String.
     * @return String representation of the error message.
     */
    public static String showErrorMessage(String s) {
        return s;
    }

    /**
     * Prints a goodbye message when the user exits the chat bot.
     *
     * @return String representation of a goodbye message.
     */
    public static String showGoodbyeMessage() {
        return "See you! :)";
    }

    /**
     * Prints an ArrayList of tasks.
     *
     * @param tasks The tasks to print.
     * @return String representation of a goodbye message.
     */
    public static String showTaskList(ArrayList<Task> tasks) {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            s += (i + 1) + ". " + task.toString() + "\n";
        }
        return s;
    }

}
