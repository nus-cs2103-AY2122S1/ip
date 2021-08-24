package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * The Command class encapsulates a command to be executed by Duke.
 * Each Command has a unique command word associated with it.
 */
public abstract class Command {

    /**
     * Executes the Command based on a given TaskList, Ui and Storage.
     *
     * @param tasks the given TaskList.
     * @param ui the given Ui.
     * @param storage the given Storage.
     * @throws DukeException when the Command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the Command is the ExitCommand, false otherwise.
     * @return true if the Command is the ExitCommand, false otherwise.
     */
    public boolean isExit() {
        return false;
    };

}
