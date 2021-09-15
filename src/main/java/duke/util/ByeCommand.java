package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

public class ByeCommand implements Command {

    /**
     * Returns string response when user enters a bye command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for bye command.
     * @throws DukeException If there is error saving the data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks, "update");
        return ui.showBye();
    }
}
