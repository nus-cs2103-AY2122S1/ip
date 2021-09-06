package duke;

import duke.tasks.Task;

/**
 * Handles all functionality related to displaying of the user interface.
 */
public class Ui {

    /**
     * Displays the goodbye message.
     */
    public String showGoodbye() {
        return "Exiting the program, goodbye!";
    }

    /**
     * Displays the loading error message.
     */
    public void showLoadingError() {
        System.out.println("No taskfile found, creating a new taskfile...\n");
    }

    /**
     * Displays the message when a Task is added to a TaskList.
     * @param task The task added to the TaskList.
     * @param taskList The TaskList with the Task added.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        assert taskList.size() > 0 : "Size of TaskList should be more than 0";
        return "Got it. I have added this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Displays the message when a Task is deleted from a TaskList.
     * @param task The task deleted from the TaskList.
     * @param taskList The TaskList the Task is deleted from.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        return "Noted. I have removed this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Displays the message shown when a Task is marked as done
     *
     * @param task The Task which is marked as done.
     */
    public String showTaskDone(Task task) {
        return "Great! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Displays an error message with the given error.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    /**
     * Displays the contents of the TaskList
     *
     * @param taskList The TaskList whose contents are to be displayed.
     */
    public String showList(TaskList taskList) {
        return "Here are the tasks in your list: " + taskList;
    }

    /**
     * Displays the matching tasks in the list.
     * @param taskList List of matching tasks.
     */
    public String showMatchingTasks(TaskList taskList) {
        return "Here are the matching tasks in your list: " + taskList;
    }

}
