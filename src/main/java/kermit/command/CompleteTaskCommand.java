package kermit.command;

import kermit.KermitException;
import kermit.Ui;
import kermit.ToDo;
import kermit.Storage;
import kermit.tasks.Task;

/**
 * Command used to mark a task as complete.
 */
public class CompleteTaskCommand extends Command {
    // zero indexed
    private int taskNum;

    /**
     * Complete task command constructor.
     *
     * @param taskNum Task number to mark as complete (one-indexed).
     * @throws KermitException if string is not a valid integer that cannot be parsed.
     */
    public CompleteTaskCommand(String taskNum) throws KermitException {
        try {
            this.taskNum = Integer.parseInt(taskNum) - 1;
        } catch (NumberFormatException e) {
            throw new KermitException("That is an invalid task number!");
        }

    }

    /**
     * Execute complete task command.
     * Tries to mark task as complete, notifies user and saves the task list.
     *
     * @param taskList Instance of task list used.
     * @param ui       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if error completing task or saving task list.
     */
    @Override
    public void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        try {
            Task task = taskList.completeTask(taskNum);
            ui.showCompleteTaskMessage(task);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new KermitException("That is an invalid task!");
        }
    }

    /**
     * Returns if command is the exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}