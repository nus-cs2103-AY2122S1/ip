package duke.commands;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command{

    public ByeCommand(Storage storage, TaskList taskList,Ui ui){
        super(storage, taskList, ui);
    }

    @Override
    public boolean exec() throws DukeException {
        ui.goodbye();
        return false;
    }
}
