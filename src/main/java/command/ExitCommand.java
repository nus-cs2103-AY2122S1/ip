package command;

import data.Storage;
import data.TaskList;
import data.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
