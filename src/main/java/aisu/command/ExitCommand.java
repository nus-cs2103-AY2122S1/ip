package aisu.command;

import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        // Does nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
