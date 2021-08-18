package commands;

import core.TaskList;
import gui.Ui;
import tasks.Task;

public abstract class Command {

    public abstract void execute(TaskList taskList);

    public abstract boolean shouldExit();
}
