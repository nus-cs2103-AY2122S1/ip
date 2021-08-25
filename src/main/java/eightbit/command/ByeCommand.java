package eightbit.command;

import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;
    }
}
