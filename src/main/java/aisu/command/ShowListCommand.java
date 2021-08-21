package aisu.command;

import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

public class ShowListCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        System.out.println(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}