package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}

