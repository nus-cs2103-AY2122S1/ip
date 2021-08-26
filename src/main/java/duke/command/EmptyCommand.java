package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class EmptyCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        // Do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
