package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles displaying the task recorded in Duke.
 */
public class CommandList extends Command {
    /**
     * Constructor for the ListCommand.
     */
    public CommandList() {}

    /**
     * Displays the recorded tasks in Duke.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayListUi(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        return obj instanceof CommandList;
    }
}
