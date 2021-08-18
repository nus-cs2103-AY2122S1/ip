package commands;

import core.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList);

    public abstract boolean shouldExit();
}
