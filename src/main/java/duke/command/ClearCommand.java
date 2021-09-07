package duke.command;

import duke.Archive;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ClearCommand extends Command {

    public ClearCommand() {
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws DukeException {
        storage.clear();
        taskList.clear();
        return ui.clear();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
