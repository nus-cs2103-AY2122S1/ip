package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.remove(line);
        ui.showAdded();

        super.execute(tasks, ui, storage);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
