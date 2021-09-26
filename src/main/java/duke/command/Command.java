package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command represents a set of instructions to be executed by Duke.
 */
public abstract class Command {
    /**
     * Executes a set of instructions in Duke, then returns the string to be displayed
     * to the user on the user interface.
     *
     * @param taskList Task list of the user loaded on Duke.
     * @param ui The object representing Duke's UI.
     * @param storage The object representing Duke's data and storage.
     * @return A string to be displayed to the user on the user interface.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
