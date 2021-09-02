package viper.commands;

import viper.storage.Storage;
import viper.tasks.TaskList;
import viper.ui.Ui;

/**
 * Exits Viper and shows a bye message.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
