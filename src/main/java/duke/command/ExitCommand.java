package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(Tasklist task, UI ui, FileManager fileManager) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
