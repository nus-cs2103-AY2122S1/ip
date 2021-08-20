package aisu.command;

import aisu.Storage;
import aisu.Tasklist;
import aisu.Ui;
import aisu.command.Command;

public class ExitCommand extends Command {

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) {
        // Does nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
