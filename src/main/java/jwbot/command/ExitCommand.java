package jwbot.command;

import jwbot.storage.Storage;
import jwbot.data.TaskList;
import jwbot.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
