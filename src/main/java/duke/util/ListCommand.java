package duke.util;

import duke.task.TaskList;

/**
 * Encapsulates the list command class.
 */
public class ListCommand implements Command {

    /**
     * Returns string response when user enters a list command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of response for list command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListMessage() + tasks.printTasksInList();
    }
}
