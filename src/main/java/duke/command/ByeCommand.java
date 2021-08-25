package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.gui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {}

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.sayBye();
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
