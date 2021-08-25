package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Shows the list of tasks stored.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks);
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return Boolean whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
