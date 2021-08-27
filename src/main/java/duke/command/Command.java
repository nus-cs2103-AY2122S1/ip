package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents the abstract Command class, which provides the skeleton
 * for all Commands.
 */
public abstract class Command {

    /**
     * This method determines what the command should do.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);

    /**
     * This method determines if the scanner should keep scanning inputs.
     *
     * @return true if yes, false if no.
     */
    public abstract boolean isExit();
}

