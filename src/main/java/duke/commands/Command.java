package duke.commands;

import duke.TaskList;

public interface Command {

    public String execute(TaskList taskList);
    public boolean isRunning();

}
