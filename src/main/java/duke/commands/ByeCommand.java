package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.main.TaskList;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //Storage
        storage.save(taskList);

        //Ui
        ui.showBye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
