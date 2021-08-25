package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the user command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Class constructor.
     * @param input The user input to indicate the task to delete.
     * @throws DukeException If the task does not exist in the TaskList.
     */
    public DeleteCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input.substring(7));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    /**
     * Executes the delete command by outputting the task to be deleted, then deleting the task and
     * outputting the number of remaining tasks in the TaskList.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.taskDeleted(taskList.getTask(index - 1));
        taskList.deleteTask(index - 1);
        ui.showTaskListSize(taskList);
    }

    /**
     * Indicates if the command ends the program after executing.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
