package duke.command;

import duke.data.Storage;
import duke.exceptions.InvalidInputException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * EditCommand represents a command to edit a task from the task list.
 */
public class EditCommand extends Command {
    /** Attributes for a EditCommand object */
    private EditType editType;
    private int taskPosition;
    private String updatedField;

    /**
     * Initialises a EditCommand object.
     *
     * @param taskNumber the task number of the task to be updated
     * @param editType the edit type of the command
     * @param updatedField the updated field of the task
     */
    public EditCommand(int taskNumber, EditType editType, String updatedField) {
        this.taskPosition = taskNumber - 1;
        this.editType = editType;
        this.updatedField = updatedField;
    }

    /**
     * Enumerations representing the edit types.
     */
    public enum EditType {
        NAME,
        DATE_AND_TIME
    }

    /**
     * Updates the task specified.
     *
     * @param taskToBeUpdated the task to be updated
     * @return the updated task
     */
    private Task update(Task taskToBeUpdated) {
        switch (editType) {
        case NAME:
            taskToBeUpdated.editTaskName(updatedField);
            return taskToBeUpdated;
        case DATE_AND_TIME:
            taskToBeUpdated.editDateAndTime(updatedField);
            return taskToBeUpdated;
        default:
            assert false;
            throw new AssertionError(editType);
        }
    }

    /**
     * Executes the EditCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @return a string message representing the updated task
     * @throws InvalidInputException if the task number provided is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (taskPosition > taskList.size() - 1) {
            throw new InvalidInputException(Message.MESSAGE_INVALID_TASK_NUMBER);
        }
        Task updatedTask = update(taskList.getTask(taskPosition));
        storage.update(taskList);
        return ui.showEditTaskMessage(updatedTask);
    }
}
