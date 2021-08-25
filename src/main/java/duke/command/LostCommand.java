package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class LostCommand extends Command {

    /**
     * Informs user that Duke is unable to understand the command.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLost();
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
