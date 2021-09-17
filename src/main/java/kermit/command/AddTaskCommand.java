package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Response;
import kermit.tasks.Task;

/**
 * AddTask command creates and adds task to task list.
 */
public abstract class AddTaskCommand extends Command {
    private Task task;

    /**
     * Constructs AddTaskCommand.
     *
     * @param task Task that is being added.
     * @param description description of the task.
     * @throws KermitException if the description is empty.
     */
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
     * @param response       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if task could not be saved.
     */
    @Override
    public String execute(TaskList taskList, Response response, Storage storage) throws KermitException {
        taskList.add(task);
        storage.save(taskList);
        return response.getAddTaskMessage(task, taskList);
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
