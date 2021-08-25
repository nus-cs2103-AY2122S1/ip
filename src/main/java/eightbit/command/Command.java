package eightbit.command;

import eightbit.EightBitException;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

public abstract class Command {

    protected boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws EightBitException;
}
