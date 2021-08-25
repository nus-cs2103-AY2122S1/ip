package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
