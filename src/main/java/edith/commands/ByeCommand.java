package edith.commands;

import edith.storage.Storage;
import edith.tasks.TaskList;
import edith.ui.Ui;

/**
 * Exits Viper and shows a bye message.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
