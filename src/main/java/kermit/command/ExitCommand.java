package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.ToDo;
import kermit.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        ui.showGoodbyeMessage();
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}