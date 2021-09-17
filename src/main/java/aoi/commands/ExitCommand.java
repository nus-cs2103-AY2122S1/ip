package aoi.commands;

import aoi.data.TaskList;
import aoi.storage.Storage;
import aoi.ui.Ui;

/**
 * Encapsulates the Exit Command for Aoi.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     * @return A string that says farewell to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showFarewellMsg();
    }
}
