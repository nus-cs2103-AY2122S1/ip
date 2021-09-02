package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents the command when the user wants to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand (int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command and deletes the task from the list of saved tasks.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     */
    public String execute(Storage storage, Ui ui) {
        try {
            int taskListLen = storage.taskListLen();
            if (taskNum + 1 <= taskListLen) {
                Task removedTask = storage.deleteTask(taskNum);
                taskListLen -= 1;
                storage.saveToFile();
                return ui.taskDeletedMessage(removedTask, taskListLen);
            } else {
                return ui.missingTaskMessage();
            }
        } catch (IndexOutOfBoundsException e) {
            return Ui.showErrorMessage("Please enter a valid index!");
        }
    }
}
