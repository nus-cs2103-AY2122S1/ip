package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates the archive command class.
 */
public class ArchiveCommand implements Command {

    /**
     * Returns string response when user enters a archive command.
     *
     * @param tasks List of tasks.
     * @param ui Ui that prints message to users.
     * @param storage Storage to save and load data.
     * @return String representation of duke's response for archive command.
     * @throws DukeException If there is error saving the data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.archiveAllTasks();
        storage.save(tasks, "update");
        return ui.showArchiveMessage();
    }
}
