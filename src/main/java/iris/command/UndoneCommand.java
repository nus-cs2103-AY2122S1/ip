package iris.command;

import iris.exception.NoSuchTaskException;
import iris.storage.Storage;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Command to mark a task in the task list as not done.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class UndoneCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor for an UndoneCommand.
     *
     * @param taskList   Handles all operations regarding tasks.
     * @param storage    Save and load data from local directory.
     * @param ui         Prints message with respect to user input.
     * @param taskNumber Task number of task to be marked as undone.
     */
    public UndoneCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes runCommand. Marks the task as undone,
     * prints a message to tell user that task has been marked as undone
     * and update local data file.
     *
     * @return String representation when Iris successfully marks a task as undone.
     */
    @Override
    public String runCommand() throws NoSuchTaskException {
        assert taskNumber <= taskList.size() : "There is no such task.";
        storage.save(taskList.getList());
        return ui.guiTaskUndoneMessage(taskList.markAsUndone(taskNumber));
    }
}
