package aisu.command;

import aisu.AisuException;
import aisu.Storage;
import aisu.Tasklist;
import aisu.Ui;

public abstract class Command {
    public abstract void execute(Tasklist tasklist, Storage storage, Ui ui) throws AisuException;

    public abstract boolean isExit();
}
