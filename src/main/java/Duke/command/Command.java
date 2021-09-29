package Duke.command;

import Duke.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks);
}