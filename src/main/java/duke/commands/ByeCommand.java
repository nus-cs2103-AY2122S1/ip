package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.main.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //Storage
        storage.save(taskList);

        //Ui
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
