package cs2103t.duke.command;

import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public abstract class Command {

    String space = Ui.SPACE;

    public abstract void execute(TaskList tasks, Ui ui);

}
