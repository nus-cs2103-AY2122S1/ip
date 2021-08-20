package aisu.command;

import aisu.Storage;
import aisu.Tasklist;
import aisu.Ui;
import aisu.command.Command;

public class ShowListCommand extends Command {
    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) {
        System.out.println(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}