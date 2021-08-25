package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

public class ExitCommand extends Command {

    public boolean isExit = true;

    public ExitCommand() {
        super(CommandType.EXIT, true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}

