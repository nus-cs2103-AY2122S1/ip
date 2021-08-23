package duke.commands;

import duke.tasktypes.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Command class.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
