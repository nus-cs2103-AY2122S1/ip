package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * UI class that handles all interactions with user.
 */
public class UI {

    public static final String LINE = "___________________________________________________\n";

    /**
     * This method is to print the starting message of Duke.
     */
    public void showStartMessage() {
        System.out.println(LINE + "Hello! I'm Duke\n" + "What can I do for you?\n" + LINE);
    }

    /**
     * This method is to print the exiting message of Duke.
     */
    public void showExitMessage() {
        System.out.println(LINE + "bye! for now...\n" + LINE);
    }

    /**
     * This method is to print the message when duke successfully adds a task.
     *
     * @param t The task that was added.
     * @param size The total number of tasks.
     */
    public void showAddTaskMessage(Task t, int size) {
        System.out.println(LINE + "I've added this task:\n" + t
                + "\n" + "You have " + size + " tasks left!\n" + LINE);
    }

    /**
     * This method is to print the message when duke successfully marks a task as complete.
     *
     * @param t The task that was completed.
     */
    public void showCompleteTaskMessage(Task t) {
        System.out.println(LINE + "Well done! You finally completed " + t.getName() + "!\n" + LINE);
    }

    /**
     * This method is to print the message when duke successfully deletes a task.
     *
     * @param t The task that was added.
     * @param size The total number of tasks.
     */
    public void showDeleteTaskMessage(Task t, int size) {
        System.out.println(LINE + "The task has been removed:\n" + t
                + "\n" + "You have " + size + " tasks left!\n" + LINE);
    }

    /**
     * This method is to print the list of all tasks in TaskList.
     *
     * @param tasklist The TaskList object where all the tasks are stored.
     */
    public void getListMessage(TaskList tasklist) {
        String message = "Here are your tasks... if you choose to do it...\n";
        String listMessage = tasklist.getAllTasks();
        System.out.println(LINE + message + listMessage + LINE);
    }

    /**
     * This method is to print the the list of all commands available to Duke.
     */
    public void showListOfCommands() {
        System.out.println(LINE + "Unknown Command!\n" + LINE);
    }

    /**
     * This method is to print the error message.
     *
     * @param errorMessage The error message of the exception thrown.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(LINE + errorMessage + "\n" + LINE);
    }

    /**
     * Prints out the task that matched with the keyword with formatting.
     * @param tasks String input of the tasks found.
     */
    public void showFoundTask(String tasks) {
        System.out.println(LINE + "Here are the matching tasks in your list:");
        System.out.println(tasks + LINE);
    }
}
