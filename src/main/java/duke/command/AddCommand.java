package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    public AddCommand(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showAdded(tasks.add(line), tasks.getSize());
        super.execute(tasks, ui, storage);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
