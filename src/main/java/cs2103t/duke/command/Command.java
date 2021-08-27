package cs2103t.duke.command;

import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command.
 *
 * <code>Command</code> is an abstract class and it has multiple subclasses.
 * A command can be executed by calling the <code>execute</code> method with required parameters.
 */
public abstract class Command {

    protected String space = Ui.SPACE;

    public abstract void execute(TaskList taskList, Ui ui);

}
