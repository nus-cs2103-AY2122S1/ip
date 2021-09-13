package duke.command;

import duke.data.exceptions.DukeException;
import duke.data.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

public class EditCommand extends Command {
    private EditType editType;
    private int taskNumber;
    private int taskPosition;
    private String updatedField;

    public EditCommand(int taskNumber, EditType editType, String updatedField) {
        this.taskNumber = taskNumber;
        this.taskPosition = taskNumber - 1;
        this.editType = editType;
        this.updatedField = updatedField;
    }

    public enum EditType {
        NAME,
        DATE_AND_TIME
    }

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
