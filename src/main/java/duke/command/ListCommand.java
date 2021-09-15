package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand return the list of task stored.
 */
public class ListCommand extends Command {

    /**
     * Constructor for List Command.
     */
    public ListCommand() {
        this.isExit = false;
    }

    /**
     * Returns the list of tasks stored.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return List of tasks stored.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        return ui.createListMessage(tasks);
    }

}
