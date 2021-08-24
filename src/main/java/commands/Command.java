package commands;

import core.Storage;
import core.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Storage storage);

    public abstract boolean shouldExit();
}
