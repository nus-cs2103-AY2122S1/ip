package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the user command to mark a task in the TaskList as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Class constructor.
     * @param input The user input to indicate the task to mark as done.
     * @throws DukeException If the task does not exist in the TaskList.
     */
    public DoneCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input.substring(5));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    /**
     * Executes the done command. The task indicated by the user is marked as done, then
     * a report of the task marked is shown to the user.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(index - 1);
        ui.taskMarked(taskList.getTask(index - 1));
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
