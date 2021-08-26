package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws KermitException;
    public abstract boolean isExit();
}
