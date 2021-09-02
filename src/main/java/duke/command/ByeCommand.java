package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to execute the exit command.
 */
public class ByeCommand implements Command {

    /**
     * Execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList.getTasks());
        ui.exit();
    }

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean true as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
