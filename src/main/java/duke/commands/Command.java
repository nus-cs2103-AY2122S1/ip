package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui);

    public abstract boolean isExit();

}
