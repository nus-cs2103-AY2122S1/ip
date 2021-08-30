package duke.command;

import duke.exception.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to remove a task from the task list.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructor for an AddCommand.
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
     */
    @Override
    public void runCommand() throws NoSuchTaskException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.taskDeletedMessage(deletedTask, taskList.size());
        storage.save(taskList.getList());
    }
}

