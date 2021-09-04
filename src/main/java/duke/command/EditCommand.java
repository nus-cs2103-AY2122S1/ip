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
    private static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "Please enter a valid task number.";

    /** The edit type to be performed. */
    private EditType editType;

    /** The user's input. */
    private String userInput;

    /**
     * Constructs an edit command with information on the task to be edited
     * and the edit to be performed.
     *
     * @param editType The type of edit to be performed on the task.
     * @param userInput The user's input.
     */
    public EditCommand(EditType editType, String userInput) {
        this.editType = editType;
        this.userInput = userInput;
    }

    /**
     * Executes the instructions for editing a task on the user's task list on Duke.
     *
     * @param taskList Task list of the user loaded on Duke.
     * @param ui The object representing Duke's UI.
     * @param storage The object representing Duke's data and storage.
     * @return A string to be displayed to the user on the user interface.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] commandAndArgument = this.userInput.split(" ", 2);
            int taskIndex = Integer.parseInt(commandAndArgument[1]) - 1;
            Task editedTask = null;
            switch (this.editType) {
            case DONE:
                editedTask = taskList.markAsDone(taskIndex);
                break;
            case DELETE:
                editedTask = taskList.delete(taskIndex);
                break;
            default:
                assert false : this.editType;
            }
            assert editedTask != null : "Failed to retrieve edited task";
            storage.overwriteSave(taskList);
            return ui.formatEditedTask(editedTask, taskList.getNumberOfTasks(), this.editType);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
        }
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
            return this.editType == otherCommand.editType && this.userInput.equals(otherCommand.userInput);
        } else {
            return false;
        }
    }
}
