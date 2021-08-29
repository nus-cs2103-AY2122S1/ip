package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an addition user command.
 * Inherits from command class.
 */
public class AddCommand extends Command {
    /**
     * Constructs AddCommand object.
     *
     * @param cmd task command to be added.
     */
    public AddCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Adds task command into TaskList.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @throws DukeException If unable to add task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showAdded(tasks.add(getLine()), tasks.getSize());
        super.execute(tasks, ui, storage);
    }

    /**
     * Returns if command exits program.
     *
     * @return if command exists program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
