package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
