package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles when Duke do not understand the command given.
 */
public class CommandUnknown extends Command {
    /**
     * Constructor for the UnknownCommand.
     */
    public CommandUnknown() {}

    /**
     * Informs user that command given is not understood.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.displayUnknownUi());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        return obj instanceof CommandUnknown;
    }
}
