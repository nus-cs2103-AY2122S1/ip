package duke.command;

import duke.exception.DukeException;
import duke.exception.Messages;
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
     * @param cmd task to be deleted.
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
     * @return chat bot response message.
     * @throws DukeException If unable to delete task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = getIndex();
            String commandResult = tasks.remove(index);
            int listSize = tasks.getSize();

            String output = ui.showDelete(commandResult, listSize);
            super.execute(tasks, ui, storage);

            return output;
        } catch (Exception e) {
            throw new DukeException(Messages.EXIST.toString());
        }
    }

    /**
     * Returns if command exits program.
     *
     * @return true if command exists program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
