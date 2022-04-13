package duke.commands;

import duke.exceptions.InvalidNumberInputException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;


/**
 * The DeleteCommand class is a Command that deletes a task from the task list
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Initialises the index of the task to be deleted
     *
     * @param index index of task which is to be deleted from the list
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the list provided the index is a valid integer within the range of the list
     * otherwise throws an InvalidNumberInputException
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        try {
            Task currTask = taskList.remove(index - 1);
            return ui.displayDelete(currTask.toString(), taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }
}