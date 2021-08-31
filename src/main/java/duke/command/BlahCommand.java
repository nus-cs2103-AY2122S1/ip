package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

public class BlahCommand extends Command {
    String command;
    public BlahCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.respondToBlah();
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
