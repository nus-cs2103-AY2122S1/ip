package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class executes the exit command
 */
public class ByeCommand implements Command {

    /**
     *
     * @param taskList Manages all current tasks
     * @param ui Used to print messages
     * @param storage Loads and saves the tasks to a txt file
     * @throws DukeException thrown if there are input/parsing errors
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList.getTasks());
        ui.exit();
    }

    /**
     * Method to determine if Duke should stop running.
     *
     * @return true as this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
