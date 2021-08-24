package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExceptionCommand extends Command {
    DukeException e;

    public ExceptionCommand(DukeException e) {
        this.e = e;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printDukeException(e.getMessage());
    }
}
