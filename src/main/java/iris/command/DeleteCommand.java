package iris.command;

import iris.exception.NoSuchTaskException;
import iris.storage.Storage;
import iris.task.Task;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Command to remove a task from the task list.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor for a DeleteCommand.
     *
     * @param taskList   Handles all operations regarding tasks.
     * @param storage    Save and load data from local directory.
     * @param ui         Prints message with respect to user input.
     * @param taskNumber Task number of task to be removed from task list.
     */
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes runCommand. Removes the task from the task list,
     * prints a message to tell user that task has been removed
     * and update local data file.
     *
     * @return String representation when Iris successfully deletes a task.
     */
    @Override
    public String runCommand() throws NoSuchTaskException {
        assert taskNumber <= taskList.size() : "There is no such task.";
        Task deletedTask = taskList.deleteTask(taskNumber);
        storage.save(taskList.getList());
        return ui.guiTaskDeletedMessage(deletedTask, taskList.size());
    }
}

