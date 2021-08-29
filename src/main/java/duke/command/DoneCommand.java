package duke.command;

import duke.exception.DukeException;
import duke.exception.Messages;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a done user command.
 * Inherits from command class.
 */
public class DoneCommand extends Command {
    /**
     * Constructs DoneCommand object.
     *
     * @param cmd task command to be completed.
     */
    public DoneCommand(String cmd) {
        super(cmd);
    }

    /**
     * Executes command.
     * Marks task as done.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @throws DukeException If unable to done task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(getLine()) - 1;
            ui.showDone(tasks.done(index));
            super.execute(tasks, ui, storage);
        } catch (Exception e) {
            throw new DukeException(Messages.EXIST.toString());
        }
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
