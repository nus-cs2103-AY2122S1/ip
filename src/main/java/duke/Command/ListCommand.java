package duke.Command;

import duke.Command.Command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
