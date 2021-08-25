package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs DeleteCommand object.
     *
     * @param taskNumber Task number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes task in Task List and Storage.
     * Send deleted message to the user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        storage.save(tasks);
        ui.showDeleteTask(deletedTask, tasks);
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return Boolean whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
