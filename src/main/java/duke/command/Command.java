package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Commands that Duke can respond to.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public abstract class Command {

    /**
     * Asks Duke to execute a specific command.
     * The method body must be implemented by its subclasses.
     *
     * @param tasks The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a boolean that tells Duke when to exit.
     *
     * @return A boolean representing the exit condition.
     */
    public abstract boolean isExit();
}
