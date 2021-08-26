package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Encapsulates a command issued to the chatbot, which produces an effect on the task list,
 * and contains a description of said effect.
 */
public abstract class Command {
    private String description;

    /**
     * Performs the operations associated with the command, then
     * updates the command description accordingly.
     *
     * @param taskList The task list to be affected by the operations.
     * @param ui       The user interface to be affected by the operations.
     * @param storage  The storage function to be affected by the operations.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        description = runAndGenerateDescription(taskList, ui, storage);
    }

    /**
     * Runs the operations and returns a description of the operations run.
     *
     * @param taskList The task list to be affected by the operations.
     * @param ui       The user interface to be affected by the operations.
     * @param storage  The storage function to be affected by the operations.
     * @return A description of the operations run.
     */
    abstract public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage);

    /**
     * Getter for the command description.
     *
     * @return The command description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
