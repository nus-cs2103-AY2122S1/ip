package captain.command;

import captain.Storage;
import captain.Ui;
import captain.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
