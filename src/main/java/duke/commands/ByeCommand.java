package commands;

import exceptions.DukeException;
import main.Ui;
import main.Storage;
import main.TaskList;

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
