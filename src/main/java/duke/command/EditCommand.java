package duke.command;

import duke.constant.EditType;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The EditCommand class encapsulates the data and instructions
 * needed to edit a task in the user's task list on Duke.
 */
public class EditCommand extends Command {
    /** The edit type to be performed. */
    private EditType editType;

    /** The index of the task of the task in the task list to be edited. */
    private int taskIndex;

    /**
     * Constructs an edit command with information on the task to be edited
     * and the edit to be performed.
     * 
     * @param editType The type of edit to be performed on the task.
     * @param taskIndex The index of the task to be edited in the task list.
     */
    public EditCommand(EditType editType, int taskIndex) {
        this.editType = editType;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the instructions for editing a task on the user's task list on Duke.
     * 
     * @param taskList Task list of the user loaded on Duke.
     * @param ui The object representing Duke's UI.
     * @param storage The object representing Duke's data and storage.
     */
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

    /**
     * Checks whether the command exits Duke.
     * 
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks whether another object is equal with this edit command.
     * 
     * @param other The object being compared to.
     * @return true if both are edit commands and share the same edit type and task index, false otherwise.
     */
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
