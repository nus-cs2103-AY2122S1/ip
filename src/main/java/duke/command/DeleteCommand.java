package duke.command;

import duke.data.Storage;
import duke.exceptions.InvalidInputException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * DeleteCommand represents a command to delete a task from a task list.
 */
public class DeleteCommand extends Command {
    /** Attribute for a DeleteCommand object */
    private int taskNumber;

    /**
     * Initialises a DeleteCommand object.
     *
     * @param taskNumber represents the task number of the task to be deleted in the current task list
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task from the current task list and returns the deleted task.
     *
     * @param tasks the current task list
     * @return the deleted task
     * @throws InvalidInputException when the task number provided is invalid
     */
    private Task deleteTask(TaskList tasks) throws InvalidInputException {
        int taskPosition = taskNumber - 1;
        if (taskPosition >= tasks.size()) {
            throw new InvalidInputException(Message.MESSAGE_INVALID_TASK_NUMBER);
        } else {
            Task removedTask = tasks.getTask(taskPosition);
            tasks.removeTask(taskPosition);
            return removedTask;
        }
    }

    /**
     * Executes the DeleteCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @return a string message of the deleted task
     * @throws InvalidInputException if the task number provided is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        Task removedTask = deleteTask(taskList);
        storage.update(taskList);
        return ui.showDeleteTaskMessage(removedTask, taskList);
    }
}
