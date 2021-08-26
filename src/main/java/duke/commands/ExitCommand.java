package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public boolean isExit = true;

    public ExitCommand() {
        super(CommandType.EXIT, true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}

