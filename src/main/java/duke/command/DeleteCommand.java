package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete command delete task from task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs DeleteCommand object.
     *
     * @param taskNumber Task number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        assert taskNumber >= 1;
        this.isExit = false;
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes task in Task List and Storage.
     * Returns deleted message to be sent to the user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return Delete message to be sent to user.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        Task deletedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        storage.save(tasks);
        return ui.createDeleteTaskMessage(deletedTask, tasks);
    }

}
