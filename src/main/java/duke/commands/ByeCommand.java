package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents exit command
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public ByeCommand() throws DukeException {
        super("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //Storage
        storage.save(taskList);

        //Ui
        return ui.showBye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
