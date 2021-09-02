package commands;

import viper.Storage;
import viper.TaskList;
import viper.Ui;

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
