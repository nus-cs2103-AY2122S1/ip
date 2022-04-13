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
        return "Hello! I'm TaskMe!\n" + "Task me with any command!\n";
    }

    /**
     * Returns the exiting message of Duke.
     */
    public String showExitMessage() {
        return "TaskMe says goodbye!\n";
    }

    /**
     * Returns  the message when duke successfully adds a task.
     *
     * @param t The task that was added.
     * @param size The total number of tasks.
     */
    public String showAddTaskMessage(Task t, int size) {
        return "TaskMe has added this task:\n" + t
                + "\n" + "You have " + size + " tasks left!\n";
    }

    /**
     * Returns the message when duke successfully marks a task as complete.
     *
     * @param t The task that was completed.
     */
    public String showCompleteTaskMessage(Task t) {
        return "TaskMe is impressed! You finally completed" + t.getName() + "!\n";
    }

    /**
     * Returns the message when duke successfully deletes a task.
     *
     * @param t The task that was added.
     * @param size The total number of tasks.
     */
    public String showDeleteTaskMessage(Task t, int size) {
        return "TaskMe has removed task:\n" + t
                + "\n" + "You have " + size + " tasks left!\n";
    }

    /**
     * Returns the list of all tasks in TaskList.
     *
     * @param tasklist The TaskList object where all the tasks are stored.
     */
    public String getListMessage(TaskList tasklist) {
        String message = "Here are your tasks! TaskMe wishes you good luck!\n";
        String listMessage = tasklist.getAllTasks();
        return message + listMessage;
    }

    /**
     * Returns the the list of all commands available to Duke.
     */
    public String showListOfCommands() {
        return "TaskMe does not understand that command!\n";
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
        return "TaskMe found these tasks in your list:"
                + tasks;
    }

    public String showSortedTask(String tasks) {
        return "TaskMe has sorted your tasks!:\n" + tasks;
    }
}
