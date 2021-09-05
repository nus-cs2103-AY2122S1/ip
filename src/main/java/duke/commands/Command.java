package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.TaskList;

/**
 * Command class.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
