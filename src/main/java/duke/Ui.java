package duke;

import duke.tasks.Task;

/**
 * Handles all functionality related to displaying of the user interface.
 */
public class Ui {

    /**
     * Displays the goodbye message.
     *
     * @return The goodbye string.
     */
    public String showGoodbye() {
        return "Exiting the program, goodbye!";
    }

    /**
     * Displays the loading error message.
     */
    public void showLoadingError() {
        System.out.println("No task file found, creating a new task file...\n");
    }

    /**
     * Displays the message when a Task is added to a TaskList.
     *
     * @param task The task added to the TaskList.
     * @param taskList The TaskList with the Task added.
     * @return A String listing all the tasks in the TaskList.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        assert taskList.size() > 0 : "Size of TaskList should be more than 0";
        return "Got it. I have added this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Displays the message when a Task is deleted from a TaskList.
     *
     * @param task The task deleted from the TaskList.
     * @param taskList The TaskList the Task is deleted from.
     * @return A String indicating which task was removed.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        return "Noted. I have removed this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Displays the message shown when a Task is marked as done
     *
     * @param task The Task which is marked as done.
     * @return A String which indicates which task was marked as done.
     */
    public String showTaskDone(Task task) {
        return "Great! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Displays an error message with the given error.
     *
     * @param error The error message to be displayed.
     * @return The String containing the error message.
     */
    public String showError(String error) {
        return "ERROR: " + error;
    }

    /**
     * Displays the contents of the TaskList
     *
     * @param taskList The TaskList whose contents are to be displayed.
     * @return A String with all the tasks in the list.
     */
    public String showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks in your list!";
        }
        return "Here are the tasks in your list: " + taskList;
    }

    /**
     * Displays the matching tasks in the list.
     *
     * @param taskList List of matching tasks.
     * @return A string with all matching tasks.
     */
    public String showMatchingTasks(TaskList taskList) {
        return "Here are the matching tasks in your list: " + taskList;
    }

    /**
     * Displays the tasks due in the next 7 days.
     *
     * @param taskList List of matching tasks.
     * @return A string with all matching tasks.
     */
    public String showReminderTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks due in the next 7 days!";
        }
        return "Reminder: These are the tasks due in the next 7 days." + taskList;
    }

    /**
     * Displays the message indicating that the tasklist has been cleared.
     *
     * @return The message indicating that the tasklist has been cleared.
     */
    public String showTasklistClear() {
        return "The tasklist has been cleared.\nNow you have 0 tasks in the list.";
    }

}
