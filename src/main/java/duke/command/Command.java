package duke.command;

import duke.data.TaskList;

public abstract class Command {
    protected TaskList taskList;

    public Command(TaskList tasklist) {
        this.taskList = tasklist;
    }

    public abstract void execute();
}
