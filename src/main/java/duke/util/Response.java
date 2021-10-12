package duke.util;

import duke.DukeException;
import duke.tasks.Task;

/**
 * Class that represents possible String responses by Duke.
 */
public class Response {

    /**
     * A Constructor for a Response object
     */
    public Response() {}

    /**
     * Returns a formatted error message in the event that the
     * persistence file cannot be read or found.
     *
     * @return A String detailing an error in loading data from the persistent storage file.
     */
    public String showLoadError() {
        return "Could not read your file :(";
    }

    /**
     * Returns a formatted start message to the user.
     *
     * @return A String containing a welcome message to the user.
     */
    public String showStartMsg() {
        return "Hello there, I'm Duke!\nWhat can I do for you?";
    }

    /**
     * Returns a formatted exit message to the user.
     *
     * @return A String containing an exit message for the user.
     */
    public String showExitMsg() {
        return "Bye! Come back again!";
    }

    /**
     * Returns a formatted message detailing a removed Task.
     *
     * @param taskList The Tasklist the Task was removed from
     * @param task The Task that was removed
     * @return A String detailing the Task that was removed.
     */
    public String showRemovedTask(Tasklist taskList, Task task) {
        String msg = "Noted. I've removed this task:\n\t"
                + task.toString()
                + "\nNow you have "
                + taskList.getTotalTasks()
                + (taskList.getTotalTasks() == 1 ? " task" : " tasks")
                + " in the list.";
        return msg;
    }

    /**
     * Returns a formatted message listing all the current Tasks
     * in the Tasklist
     *
     * @param taskList The Tasklist of Tasks to be shown to the user.
     * @return A String detailing all the Tasks in the current Tasklist.
     */
    public String listAllTasks(Tasklist taskList) {
        return ("Here are the items in your list:\n" + taskList.toString());
    }

    /**
     * Returns a formatted message detailing a completed Task.
     *
     * @param task The Task that was completed
     * @return A String detailing the Task that was marked as complete.
     */
    public String showCompletedTask(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task.toString());
    }

    /**
     * Return a formatted message detailing an added Task.
     *
     * @param taskList The Tasklist where the Task was added to
     * @param task The Task that was added
     * @return A String detailing the Task that was added.
     */
    public String showAddedTask(Tasklist taskList, Task task) {
        String msg = "Got it. I've added this task:\n\t"
                + task.toString()
                + "\nNow you have "
                + taskList.getTotalTasks()
                + (taskList.getTotalTasks() == 1 ? " task " : " tasks ")
                + "in the list.";
        return msg;
    }

    /**
     * Returns a formatted message detailing an error from a
     * DukeException
     *
     * @param e The DukeException to be printed
     * @return A String detailing the DukeException.
     */
    public String showErrorMsg(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns a formatted message detailing the tasks that match a given
     * search term provided by the user.
     *
     * @param matches the Tasklist containing all Tasks that match the search terms
     * @return A String detailing all matching Tasks found by Duke.
     */
    public String showMatchingTasks(Tasklist matches) {
        if (matches.getTotalTasks() == 0) {
            String msg = "No matches found! :(";
            return msg;
        }

        String msg = "Here are the matching tasks in your list:\n";
        msg += matches.toString();
        return msg;
    }
}
