package jwbot.command;

import jwbot.storage.Storage;
import jwbot.data.TaskList;
import jwbot.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
