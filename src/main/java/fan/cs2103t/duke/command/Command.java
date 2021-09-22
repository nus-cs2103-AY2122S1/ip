package fan.cs2103t.duke.command;

import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command.
 *
 * <code>Command</code> is an abstract class and it has multiple subclasses.
 * A command can be executed by calling the <code>execute</code> method with required parameters.
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui);

}
