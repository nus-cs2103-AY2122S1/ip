package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {
    String line;

    /**
     * Constructs Command object.
     *
     * @param cmd task command.
     */
    public Command(String cmd) {
        line = cmd;
    }

    /**
     * Executes command.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @throws DukeException If errors occur within list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTasks());
    }

    /**
     * Returns if command exits program.
     */
    public boolean isExit() {
        return false;
    }
}
