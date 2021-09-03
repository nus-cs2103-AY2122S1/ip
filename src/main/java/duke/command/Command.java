package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList the TaskList used during execution.
     * @param storage the Storage used during execution.
     * @param ui the Ui used during execution.
     */
    public abstract String execute(TaskList taskList, Storage storage, Ui ui);

    /**
     * Checks whether the command is the exit command.
     *
     * @return Whether the command is the exit command.
     */
    public abstract boolean isExit();
}
