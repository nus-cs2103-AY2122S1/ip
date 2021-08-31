package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class encapsulates the instructions needed
 * for listing out the user's current task list Duke.
 */
public class ListCommand extends Command {
    /**
     * Executes the instructions for listing the current task list on Duke.
     *
     * @param taskList Task list of the user loaded on Duke.
     * @param ui The object representing Duke's UI.
     * @param storage The object representing Duke's data and storage.
     * @return A string to be displayed to the user on the user interface.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.formatTaskList(taskList);
    }

    /**
     * Checks whether another object is equal with this list command.
     *
     * @param other The object being compared to.
     * @return true if both are list commands, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
