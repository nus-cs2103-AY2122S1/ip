package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        return Ui.printGoodbyeMessage();
    }
}
