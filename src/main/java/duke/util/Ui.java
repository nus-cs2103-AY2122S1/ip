package duke.util;

import duke.task.Task;

/**
 * A class that handles the formatting and printing of messages in response to user inputs.
 */
public class Ui {
    private static final String FORMAT = "%s\n";

    /**
     * Creates an instance of the Ui class.
     */
    public Ui() {
    }

    /**
     * Displays the greeting message. Called when the chat bot starts up.
     */
    public String showIntroduction() {
        return String.format(FORMAT, "Hello there, I'm Duke!")
                + String.format(FORMAT, "What can I do for you today?");
    }

    /**
     * Displays a formatted message.
     *
     * @param message String that is to be formatted and printed.
     * @return A formatted string.
     */
    public String showMessage(String message) {
        return String.format(FORMAT, message);
    }

    /**
     * Displays a formatted message. Called when a new task is added.
     *
     * @param taskListSize The size of the TaskList object containing the new task.
     * @param newTask The new task that has been added.
     * @return A formatted string.
     */
    public String showAddTaskMessage(int taskListSize, Task newTask) {
        String numOfTasksString = String.format("Now you have %d task%s in the list.",
                taskListSize, taskListSize == 1 ? "" : "s");
        return showMessage("Got it. The following task has been added: ")
                + showMessage(newTask.toString())
                + showMessage(numOfTasksString);
    }

    /**
     * Displays a formatted message. Called when a task is deleted.
     *
     * @param taskListSize The size of the TaskList object containing the new task.
     * @param removedTask The task that has been deleted.
     * @return A formatted string.
     */
    public String showDeleteTaskMessage(int taskListSize, Task removedTask) {
        String numOfTasksString = String.format("Now you have %d task%s in the list.",
                taskListSize, taskListSize == 1 ? "" : "s");
        return showMessage("Got it. The following task has been removed:")
                + showMessage(removedTask.toString())
                + showMessage(numOfTasksString);
    }

    /**
     * Displays a formatted message. Called when a task is marked as done.
     *
     * @param taskListSize The size of the TaskList object.
     * @param doneTask The task that has been marked as done.
     * @return A formatted string.
     */
    public String showDoneTaskMessage(Task doneTask) {
        return showMessage("Good work! This task is now marked as done:")
                + showMessage(doneTask.toString());
    }

    /**
     * Returns a formatted error string.
     *
     * @param errorMessage Error string that is to be formatted and printed.
     * @return A formatted error string.
     */
    public String showError(String errorMessage) {
        return String.format("Uh-oh! %s\n", errorMessage);
    }

    /**
     * Returns a formatted error string. Is called if the save file does not exist.
     *
     * @return A formatted error string.
     */
    public String showFileNotFoundError() {
        return String.format(FORMAT, "This appears to be your first time using Duke.")
                + String.format(FORMAT, "A save file will be created to save your tasks when you first add a task.");
    }

    /**
     * Returns a formatted error string. Is called if the save file contains incorrectly
     * formatted data.
     *
     * @return A formatted error string.
     */
    public String showLoadingError(String errorMessage) {
        return showError(errorMessage)
                + String.format(FORMAT, "This appears to be an error with your save file.")
                + String.format(FORMAT, "Either edit data/tasks.txt to rectify the error, or delete it.")
                + String.format(FORMAT, "For now, you'll start with an empty task list.");
    }
}
