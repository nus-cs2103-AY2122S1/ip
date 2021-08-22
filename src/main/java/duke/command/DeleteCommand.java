package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a deletion user command.
 * Inherits from command class.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs DeleteCommand object.
     *
     * @param cmd task command to be deleted.
     */
    public DeleteCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Deletes task command from TaskList.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @throws DukeException If unable to delete task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.remove(line);
        ui.showAdded();
    }

    /**
     * Returns if command exits program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
