package captain.command;

import captain.Storage;
import captain.Ui;
import captain.task.TaskList;
import javafx.application.Platform;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
