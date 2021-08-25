package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.ToDo;
import kermit.Ui;

public abstract class Command {

    public abstract void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException;
    public abstract boolean isExit();
}
