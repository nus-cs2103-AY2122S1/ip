package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class used to list all tasks.
 */
public class ListCommand implements Command {

    /**
     * Method to execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printAll(taskList.getTasks(), "Here are the tasks in your list");
    }

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
