package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.execute();
        ui.showBye();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
