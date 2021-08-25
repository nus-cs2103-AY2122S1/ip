package saber.commands;

import saber.TaskList;

public abstract class SaberCommand {
    public abstract void execute(TaskList taskList);
    public abstract boolean isExit();
}
