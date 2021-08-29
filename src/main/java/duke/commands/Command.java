package duke.commands;

import duke.TaskList;
import duke.Ui;

public interface Command {

    public String execute(TaskList taskList, Ui ui);
    public boolean isRunning();

}
