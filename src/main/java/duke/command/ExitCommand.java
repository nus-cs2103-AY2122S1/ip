package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit user command.
 * Inherits from command class.
 */
public class ExitCommand extends Command {
    /**
     * Constructs ExitCommand object.
     *
     * @param cmd task command.
     */
    public ExitCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Runs all task of TaskList.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @throws DukeException If errors occur within list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.execute();
        ui.showBye();
    }

    /**
     * Returns if command exits program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
