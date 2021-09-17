package aoi.commands;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.storage.Storage;
import aoi.ui.Ui;

public class HelpCommand extends Command {
    /**
     * Executes the Help command.
     *
     * @param tasks   TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AoiException {
        String str = Ui.showHelpMsg();
        return str;
    }
}
