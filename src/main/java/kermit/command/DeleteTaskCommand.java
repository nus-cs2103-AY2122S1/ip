package kermit.command;

import kermit.KermitException;
import kermit.Ui;
import kermit.TaskList;
import kermit.Storage;
import kermit.tasks.Task;

/**
 * Command used to delete a task.
 */
public class DeleteTaskCommand extends Command {
    // zero-indexed
    private int taskNum;

    /**
     * Delete task command constructor.
     *
     * @param taskNum Task number to delete (one-indexed).
     * @throws KermitException if string is not a valid integer that cannot be parsed.
     */
    public DeleteTaskCommand(String taskNum) throws KermitException {
        try {
            this.taskNum = Integer.parseInt(taskNum) - 1;
        } catch (NumberFormatException e) {
            throw new KermitException("That is an invalid task number!");
        }

    }

    /**
     * Execute delete task command.
     * Tries to delete task, notifies user and saves the task list.
     *
     * @param taskList Instance of task list used.
     * @param ui       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if error deleting task or saving task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KermitException {
        try {
            Task deletedTask = taskList.deleteTask(taskNum);
            ui.showDeleteTaskMessage(deletedTask, taskList);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new KermitException("That is an invalid task!");
        }
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}