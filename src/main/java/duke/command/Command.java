package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class representing commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   Up to date TaskList.
     * @param ui      User interaction class.
     * @param storage Class that store tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
