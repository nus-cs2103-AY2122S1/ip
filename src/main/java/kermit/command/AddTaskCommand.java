package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Ui;
import kermit.tasks.Task;

/**
 * AddTask command creates and adds task to task list.
 */
public abstract class AddTaskCommand extends Command {
    private Task task;

    protected AddTaskCommand(Task task, String description) throws KermitException {
        if (description.equals("")) {
            throw new KermitException("The description cannot be empty!");
        }

        this.task = task;
    }

    /**
     * Executes add task command.
     * Adds task to task list, notify user and save task list.
     *
     * @param taskList Instance of task list used.
     * @param ui       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if task could not be saved.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws KermitException {
        taskList.add(task);
        storage.save(taskList);
        return ui.getAddTaskMessage(task, taskList);
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
