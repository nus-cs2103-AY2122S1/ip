package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * UI class that handles all interactions with user.
 */
public class UI {
    /**
     * Returns the starting message of Duke.
     */
    public String showStartMessage() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Returns the exiting message of Duke.
     */
    public String showExitMessage() {
        return "bye! for now...\n";
    }

    /**
     * Returns  the message when duke successfully adds a task.
     *
     * @param t The task that was added.
     * @param size The total number of tasks.
     */
    public String showAddTaskMessage(Task t, int size) {
        return "I've added this task:\n" + t
                + "\n" + "You have " + size + " tasks left!\n";
    }

    /**
     * Returns the message when duke successfully marks a task as complete.
     *
     * @param t The task that was completed.
     */
    public String showCompleteTaskMessage(Task t) {
        return "Well done! You finally completed " + t.getName() + "!\n";
    }

    /**
     * Returns the message when duke successfully deletes a task.
     *
     * @param t The task that was added.
     * @param size The total number of tasks.
     */
    public String showDeleteTaskMessage(Task t, int size) {
        return "The task has been removed:\n" + t
                + "\n" + "You have " + size + " tasks left!\n";
    }

    /**
     * Returns the list of all tasks in TaskList.
     *
     * @param tasklist The TaskList object where all the tasks are stored.
     */
    public String getListMessage(TaskList tasklist) {
        String message = "Here are your tasks... if you choose to do it...\n";
        String listMessage = tasklist.getAllTasks();
        return message + listMessage;
    }

    /**
     * Returns the the list of all commands available to Duke.
     */
    public String showListOfCommands() {
        return "Unknown Command!\n";
    }

    /**
     * Returns the error message.
     *
     * @param errorMessage The error message of the exception thrown.
     */
    public String showErrorMessage(String errorMessage) {
        return errorMessage + "\n";
    }

    /**
     * Returns the task that matched with the keyword with formatting.
     *
     * @param tasks String input of the tasks found.
     */
    public String showFoundTask(String tasks) {
        return "Here are the matching tasks in your list:"
                + tasks;
    }
}
