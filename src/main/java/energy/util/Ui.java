package energy.util;

import energy.task.Task;

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
     * Displays the greeting message.
     */
    public String showIntroduction() {
        return String.format(FORMAT, "Hello there, I'm Energy!")
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
     * Displays a formatted message which remarks that a task has been added.
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
     * Displays a formatted message which remarks that a task has been deleted.
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
     * Displays a formatted message which remarks that a task has been marked as done.
     *
     * @param doneTask The task that has been marked as done.
     * @return A formatted string.
     */
    public String showDoneTaskMessage(Task doneTask) {
        return showMessage("Good work! This task is now marked as done:")
                + showMessage(doneTask.toString());
    }

    /**
     * Displays a formatted message which remarks that an alias has been added.
     *
     * @param alias The alias being added.
     * @param command The command that corresponds to the alias being added.
     * @return A formatted string.
     */
    public String showAddAliasMessage(String alias, String command) {
        String outputString = String.format("Got it. Alias %s now corresponds to command %s.",
                alias, command);
        return showMessage(outputString);
    }

    /**
     * Displays a formatted message which remarks that an alias has been deleted.
     *
     * @param alias The alias being deleted.
     * @param command The command that corresponds to the alias being deleted.
     * @return A formatted string.
     */
    public String showDeleteAliasMessage(String alias, String command) {
        String outputString = String.format("Got it. Alias %s no longer corresponds to command %s.",
                alias, command);
        return showMessage(outputString);
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
     * Returns a formatted error string which remarks that the save file does not exist.
     *
     * @return A formatted error string.
     */
    public String showSaveFileNotFoundError() {
        return String.format(FORMAT, "This appears to be your first time using Energy.")
                + String.format(FORMAT, "A save file will be created to save your tasks.");
    }

    /**
     * Returns a formatted error string which remarks that the config file does not exist.
     *
     * @return A formatted error string.
     */
    public String showConfigFileNotFoundError() {
        return String.format(FORMAT, "A config file will be created to save your settings.");
    }

    /**
     * Returns a formatted error string which remarks that the save file contains incorrectly
     * formatted data.
     *
     * @return A formatted error string.
     */
    public String showSaveFileLoadingError(String errorMessage) {
        return showError(errorMessage)
                + String.format(FORMAT, "This appears to be an error with your save file.")
                + String.format(FORMAT, "Either edit data/tasks.txt to rectify the error, or delete it.")
                + String.format(FORMAT, "For now, you'll start with an empty task list.");
    }

    /**
     * Returns a formatted error string which remarks that the config file contains incorrectly
     * formatted data.
     *
     * @return A formatted error string.
     */
    public String showConfigLoadingError(String errorMessage) {
        return showError(errorMessage)
                + String.format(FORMAT, "This appears to be an error with your config file.")
                + String.format(FORMAT, "Either edit data/config.txt to rectify the error, or delete it.")
                + String.format(FORMAT, "For now, you'll start without any aliases for your commands.");
    }
}
