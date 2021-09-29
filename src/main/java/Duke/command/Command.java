package Duke.command;

import java.util.ArrayList;

import Duke.TaskList;
import Duke.task.Task;

public abstract class Command {
    public abstract String execute(TaskList tasks);
}