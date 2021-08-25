package duke.command;

import duke.constant.EditType;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EditCommand extends Command {
    private EditType editType;
    private int taskIndex;

    public EditCommand(EditType editType, int taskIndex) {
        this.editType = editType;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task editedTask;
            switch (this.editType) {
            case DONE:
                editedTask = taskList.markAsDone(this.taskIndex);
                break;
            case DELETE:
                editedTask = taskList.delete(this.taskIndex);
                break;
            default:
                throw new DukeException("Unknown command.");
            }
            storage.overwriteSave(taskList);
            ui.updateUserOnEditedTask(editedTask, taskList.getNumberOfTasks(), this.editType);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof EditCommand) {
            EditCommand otherCommand = (EditCommand) other;
            return this.editType == otherCommand.editType && this.taskIndex == otherCommand.taskIndex;
        } else {
            return false;
        }
    }
}
